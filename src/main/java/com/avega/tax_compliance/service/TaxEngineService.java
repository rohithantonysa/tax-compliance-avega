package com.avega.tax_compliance.service;

import com.avega.tax_compliance.dto.TransactionDTO;
import com.avega.tax_compliance.entity.ComplianceStatus;
import com.avega.tax_compliance.entity.FinancialTransaction;
import com.avega.tax_compliance.entity.ValidationStatus;
import com.avega.tax_compliance.repository.FinancialTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxEngineService {
	
	@Autowired
	private RuleEngineService ruleEngineService;
	
    @Autowired
    private FinancialTransactionRepository transactionRepository;

    public void processTransactionBatch(List<TransactionDTO> batch) {
        List<FinancialTransaction> transactionsToSave = new ArrayList<>();

        for (TransactionDTO dto : batch) {
            FinancialTransaction transaction = new FinancialTransaction();
            
            transaction.setTransactionId(dto.getTransactionId());
            transaction.setDate(dto.getDate());
            transaction.setCustomerId(dto.getCustomerId());
            transaction.setAmount(dto.getAmount());
            transaction.setTaxRate(dto.getTaxRate());
            transaction.setReportedTax(dto.getReportedTax());
            transaction.setTransactionType(dto.getTransactionType());

            try {
                calculateTaxAndCompliance(transaction);
                transaction.setValidationStatus(ValidationStatus.SUCCESS);
            } catch (Exception e) {
                transaction.setValidationStatus(ValidationStatus.FAILURE);
                transaction.setFailureReason("Calculation Error: " + e.getMessage());
            }

            transactionsToSave.add(transaction);
        }

        transactionRepository.saveAll(transactionsToSave);
        
        
        for (FinancialTransaction savedTx : transactionsToSave) {
            if (savedTx.getValidationStatus() == ValidationStatus.SUCCESS) {
                ruleEngineService.runRules(savedTx);
            }
        }
    }

    private void calculateTaxAndCompliance(FinancialTransaction tx) {
        BigDecimal expected = tx.getAmount().multiply(tx.getTaxRate());
        tx.setExpectedTax(expected);

        if (tx.getReportedTax() == null) {
            tx.setTaxGap(expected); 
            tx.setComplianceStatus(ComplianceStatus.NON_COMPLIANT);
            return;
        }

        BigDecimal gap = expected.subtract(tx.getReportedTax());
        tx.setTaxGap(gap);

        if (gap.compareTo(new BigDecimal("1")) > 0) {
            tx.setComplianceStatus(ComplianceStatus.UNDERPAID);
        } else if (gap.compareTo(new BigDecimal("-1")) < 0) {
            tx.setComplianceStatus(ComplianceStatus.OVERPAID);
        } else {
            tx.setComplianceStatus(ComplianceStatus.COMPLIANT); // |taxGap| <= 1
        }
    }
}
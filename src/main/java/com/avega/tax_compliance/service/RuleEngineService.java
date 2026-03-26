package com.avega.tax_compliance.service;

import com.avega.tax_compliance.entity.ExceptionRecord;
import com.avega.tax_compliance.entity.FinancialTransaction;
import com.avega.tax_compliance.entity.TaxRule;
import com.avega.tax_compliance.repository.ExceptionRecordRepository;
import com.avega.tax_compliance.repository.TaxRuleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RuleEngineService {

    @Autowired
    private TaxRuleRepository ruleRepository;

    @Autowired
    private ExceptionRecordRepository exceptionRepository;
    
   
    @Autowired
    private ObjectMapper objectMapper;

    public void runRules(FinancialTransaction tx) {
        List<TaxRule> activeRules = ruleRepository.findByIsActiveTrue();

        for (TaxRule rule : activeRules) {
            try {
                JsonNode config = objectMapper.readTree(rule.getRuleConfiguration());

                switch (rule.getRuleName()) {
                    case "HIGH_VALUE_TRANSACTION":
                        BigDecimal threshold = new BigDecimal(config.get("threshold").asText());
                        if (tx.getAmount().compareTo(threshold) > 0) {
                            createException(tx, rule, "Transaction amount exceeds threshold of " + threshold);
                        }
                        break;

                    case "GST_SLAB_VIOLATION":
                        BigDecimal slabLimit = new BigDecimal(config.get("slabThreshold").asText());
                        BigDecimal reqRate = new BigDecimal(config.get("requiredRate").asText());
                        if (tx.getAmount().compareTo(slabLimit) > 0 && tx.getTaxRate().compareTo(reqRate) < 0) {
                            createException(tx, rule, "Tax rate " + tx.getTaxRate() + " is below required " + reqRate + " for high-value slab");
                        }
                        break;

                    case "REFUND_VALIDATION":
                        if (tx.getTransactionType().name().equals("REFUND") && tx.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                            createException(tx, rule, "Refund amounts should be negativee");
                        }
                        break;		
                }
            } catch (Exception e) {
                System.err.println("Error processing rule " + rule.getRuleName() + ": " + e.getMessage());
            }
        }
    }

    private void createException(FinancialTransaction tx, TaxRule rule, String message) {
        ExceptionRecord ex = new ExceptionRecord();
        ex.setTransactionId(tx.getTransactionId());
        ex.setCustomerId(tx.getCustomerId());
        ex.setRuleName(rule.getRuleName());
        ex.setSeverity(rule.getSeverity());
        ex.setMessage(message);
        ex.setTimestamp(LocalDateTime.now());
        exceptionRepository.save(ex);
    }
}
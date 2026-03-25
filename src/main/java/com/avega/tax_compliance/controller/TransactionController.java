package com.avega.tax_compliance.controller;

import lombok.RequiredArgsConstructor;
import com.avega.tax_compliance.dto.TransactionDTO;
import com.avega.tax_compliance.entity.FinancialTransaction;
import com.avega.tax_compliance.repository.FinancialTransactionRepository;
import com.avega.tax_compliance.service.TaxEngineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    
	private final TaxEngineService taxEngineService;
    private final FinancialTransactionRepository transactionRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTransactions(@RequestBody @Valid List<TransactionDTO> batch) {
        
        taxEngineService.processTransactionBatch(batch);
        
        return ResponseEntity.ok("Batch processed successfully. Check the database for results!");
    }
    
    @GetMapping
    public ResponseEntity<List<FinancialTransaction>> getAllTransactions() {
        List<FinancialTransaction> allTransactions = transactionRepository.findAll();
        return ResponseEntity.ok(allTransactions);
    }
}
package com.avega.tax_compliance.controller;

import com.avega.tax_compliance.dto.TransactionDTO;
import com.avega.tax_compliance.service.TaxEngineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TaxEngineService taxEngineService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTransactions(@RequestBody @Valid List<TransactionDTO> batch) {
        
        taxEngineService.processTransactionBatch(batch);
        
        return ResponseEntity.ok("Batch processed successfully. Check the database for results!");
    }
}
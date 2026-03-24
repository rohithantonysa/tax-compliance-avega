package com.avega.tax_compliance.controller;

import com.avega.tax_compliance.repository.FinancialTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private FinancialTransactionRepository transactionRepository;

    @GetMapping("/summary")
    public ResponseEntity<List<Map<String, Object>>> getCustomerSummary() {
        List<Object[]> data = transactionRepository.getCustomerComplianceReport();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId", row[0]);
            map.put("totalVolume", row[1]);
            map.put("totalTaxGap", row[2]);
            map.put("transactionCount", row[3]);
            response.add(map);
        }

        return ResponseEntity.ok(response);
    }
}
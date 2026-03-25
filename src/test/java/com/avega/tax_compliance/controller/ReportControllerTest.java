package com.avega.tax_compliance.controller;

import com.avega.tax_compliance.repository.FinancialTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    private FinancialTransactionRepository transactionRepository;

    @InjectMocks
    private ReportController reportController;

    @Test
    void testGetCustomerSummary() {
        // 1. Arrange: Create fake database output (Object array)
        List<Object[]> mockDatabaseResult = new ArrayList<>();
        Object[] fakeRow = new Object[]{"CUST-999", 50000.0, 2000.0, 5L}; 
        mockDatabaseResult.add(fakeRow);

        // Tell the mock repository to return our fake data
        when(transactionRepository.getCustomerComplianceReport()).thenReturn(mockDatabaseResult);

        // 2. Act: Call the controller method
        ResponseEntity<List<Map<String, Object>>> response = reportController.getCustomerSummary();

        // 3. Assert: Verify the controller successfully mapped the Object[] to a JSON Map
        assertEquals(200, response.getStatusCode());
        
        List<Map<String, Object>> responseBody = response.getBody();
        assertEquals(1, responseBody.size());
        assertEquals("CUST-999", responseBody.get(0).get("customerId"));
        assertEquals(50000.0, responseBody.get(0).get("totalVolume"));
        assertEquals(2000.0, responseBody.get(0).get("totalTaxGap"));
        assertEquals(5L, responseBody.get(0).get("transactionCount"));
    }
}
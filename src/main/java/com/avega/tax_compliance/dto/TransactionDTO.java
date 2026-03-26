package com.avega.tax_compliance.dto;
import lombok.Data;
import com.avega.tax_compliance.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {

    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Amount is required")
   // @Positive
    private BigDecimal amount;

    @NotNull(message = "Tax Rate is required")
    private BigDecimal taxRate;

    private BigDecimal reportedTax; 

    @NotNull(message = "Transaction Type is required")
    private TransactionType transactionType;

	

}
package com.avega.tax_compliance.dto;
import lombok.Data;
import com.avega.tax_compliance.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
   // @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Tax Rate is required")
    private BigDecimal taxRate;

    private BigDecimal reportedTax; // Can be null (which means non-compliant)

    @NotNull(message = "Transaction Type is required")
    private TransactionType transactionType;

	

}
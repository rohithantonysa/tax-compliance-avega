package com.avega.tax_compliance.dto;

import com.avega.tax_compliance.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO {

    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Tax Rate is required")
    private BigDecimal taxRate;

    private BigDecimal reportedTax; // Can be null (which means non-compliant)

    @NotNull(message = "Transaction Type is required")
    private TransactionType transactionType;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getReportedTax() {
		return reportedTax;
	}

	public void setReportedTax(BigDecimal reportedTax) {
		this.reportedTax = reportedTax;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

}
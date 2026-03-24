package com.avega.tax_compliance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String transactionId;

    private LocalDate date;
    private String customerId;
    private BigDecimal amount;
    private BigDecimal taxRate;
    private BigDecimal reportedTax;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal expectedTax;
    private BigDecimal taxGap;

    @Enumerated(EnumType.STRING)
    private ValidationStatus validationStatus;
    
    @Column(length = 500)
    private String failureReason;

    @Enumerated(EnumType.STRING)
    private ComplianceStatus complianceStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getExpectedTax() {
		return expectedTax;
	}

	public void setExpectedTax(BigDecimal expectedTax) {
		this.expectedTax = expectedTax;
	}

	public BigDecimal getTaxGap() {
		return taxGap;
	}

	public void setTaxGap(BigDecimal taxGap) {
		this.taxGap = taxGap;
	}

	public ValidationStatus getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(ValidationStatus validationStatus) {
		this.validationStatus = validationStatus;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public ComplianceStatus getComplianceStatus() {
		return complianceStatus;
	}

	public void setComplianceStatus(ComplianceStatus complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

    
}
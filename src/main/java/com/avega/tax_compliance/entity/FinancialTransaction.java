package com.avega.tax_compliance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
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


    
}
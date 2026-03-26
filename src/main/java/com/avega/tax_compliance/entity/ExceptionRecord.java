package com.avega.tax_compliance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "exception_records")
public class ExceptionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String customerId;
    private String ruleName;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private String message;
    private LocalDateTime timestamp;
	

   
}
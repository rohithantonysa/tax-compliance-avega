package com.avega.tax_compliance.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "tax_rules")
public class TaxRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Column(columnDefinition = "TEXT")
    private String ruleConfiguration;


    
}
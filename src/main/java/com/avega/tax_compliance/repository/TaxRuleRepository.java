package com.avega.tax_compliance.repository;

import com.avega.tax_compliance.entity.TaxRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRuleRepository extends JpaRepository<TaxRule, Long> {
    List<TaxRule> findByIsActiveTrue();
}
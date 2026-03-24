package com.avega.tax_compliance.repository;

import com.avega.tax_compliance.entity.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    
    Optional<FinancialTransaction> findByTransactionId(String transactionId);

    @Query(value = "SELECT customer_id, " +
           "SUM(amount) as total_volume, " +
           "SUM(tax_gap) as total_gap, " +
           "COUNT(*) as txn_count " +
           "FROM transactions GROUP BY customer_id", nativeQuery = true)
    List<Object[]> getCustomerComplianceReport();
}
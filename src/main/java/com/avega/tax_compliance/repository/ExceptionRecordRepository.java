package com.avega.tax_compliance.repository;

import com.avega.tax_compliance.entity.ExceptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRecordRepository extends JpaRepository<ExceptionRecord, Long> {
}
package com.enigmacamp.loan_app.repository;

import com.enigmacamp.loan_app.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, String> {
}

package com.enigmacamp.loan_app.entity;

import com.enigmacamp.loan_app.constant.LoanStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "transaction_date")
    private Long transactionDate;

    @Column
    private Double nominal;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "trx_loan_id")
    private LoanTransaction loanTransaction;

    @Column(name = "loan_status")
    private LoanStatus loanStatus; // enum

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;
}

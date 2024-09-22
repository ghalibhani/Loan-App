package com.enigmacamp.loan_app.entity;

import com.enigmacamp.loan_app.constant.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trx_loan")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private Double nominal;

    @Column(name = "approved_at")
    private Long approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "loanTransaction")
    @JsonManagedReference
    private List<LoanTransactionDetail> loanTransactionDetails;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;
}

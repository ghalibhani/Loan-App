package com.enigmacamp.loan_app.dto.response;

import com.enigmacamp.loan_app.constant.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionDetailResponse {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private LoanStatus loanStatus;
    private Long createdAt;
    private Long updatedAt;
}

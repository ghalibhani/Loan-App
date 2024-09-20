package com.enigmacamp.loan_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String id;
    private String customer;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private List<LoanTransactionDetailResponse> loanTransactionDetailResponses;
}

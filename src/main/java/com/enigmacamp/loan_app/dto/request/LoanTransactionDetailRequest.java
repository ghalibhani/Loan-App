package com.enigmacamp.loan_app.dto.request;

import com.enigmacamp.loan_app.constant.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionDetailRequest {
    private Double nominal;
    private LoanStatus loanStatus;
}

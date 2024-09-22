package com.enigmacamp.loan_app.dto.request;

import com.enigmacamp.loan_app.entity.Customer;
import com.enigmacamp.loan_app.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionRequest {
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
}

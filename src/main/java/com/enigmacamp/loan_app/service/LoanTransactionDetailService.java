package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.response.LoanTransactionDetailResponse;
import com.enigmacamp.loan_app.entity.LoanTransactionDetail;

import java.util.List;

public interface LoanTransactionDetailService {
    List<LoanTransactionDetail> createLoanTransactionDetail(List<LoanTransactionDetail> loanTransactionDetailList);
    LoanTransactionDetailResponse getLoanTransactionDetailById(String id);
}

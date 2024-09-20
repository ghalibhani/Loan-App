package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.request.LoanTransactionRequest;
import com.enigmacamp.loan_app.dto.response.LoanTransactionResponse;

import java.util.List;

public interface LoanTransactionService {
    LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request);
    List<LoanTransactionResponse> getAllLoanTransaction();
    LoanTransactionResponse getLoanTransactionById(String id);
}

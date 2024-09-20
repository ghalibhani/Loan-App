package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.request.LoanTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.loan_app.dto.response.LoanTypeResponse;
import com.enigmacamp.loan_app.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoanType(LoanTypeRequest request);
    LoanTypeResponse getLoanTypeById(String id);
    LoanType getById(String id);
    List<LoanType> getAllLoanType();
    LoanTypeResponse updateLoanType(UpdateLoanTypeRequest request);
    void deleteLoanType(String id);
}

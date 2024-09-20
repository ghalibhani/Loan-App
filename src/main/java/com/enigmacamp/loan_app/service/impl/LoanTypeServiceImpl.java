package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.dto.request.LoanTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.loan_app.dto.response.LoanTypeResponse;
import com.enigmacamp.loan_app.entity.LoanType;
import com.enigmacamp.loan_app.repository.LoanTypeRepository;
import com.enigmacamp.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse createLoanType(LoanTypeRequest request) {
        try {
            LoanType loanType = (LoanType.builder()
                    .type(request.getType())
                    .maxLoan(request.getMaxLoan())
                    .build());
            loanTypeRepository.saveAndFlush(loanType);
            return convertToLoanTypeResponse(loanType);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "test");
        }
    }

    @Override
    public LoanTypeResponse getLoanTypeById(String id) {
        LoanType loanType = findByIdOrThrowNotFound(id);
        return convertToLoanTypeResponse(loanType);
    }

    @Override
    public List<LoanType> getAllLoanType() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanTypeResponse updateLoanType(UpdateLoanTypeRequest request) {
        try {
            LoanType loanType = findByIdOrThrowNotFound(request.getId());
            loanType.setType(request.getType());
            loanType.setMaxLoan(request.getMaxLoan());
            loanTypeRepository.saveAndFlush(loanType);
            return convertToLoanTypeResponse(loanType);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "test");
        }
    }

    @Override
    public void deleteLoanType(String id) {
        LoanType loanType = findByIdOrThrowNotFound(id);
        loanTypeRepository.delete(loanType);
    }

    private LoanType findByIdOrThrowNotFound(String id) {
        return loanTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Type not found"));
    }

    private LoanTypeResponse convertToLoanTypeResponse(LoanType loanType) {
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }
}

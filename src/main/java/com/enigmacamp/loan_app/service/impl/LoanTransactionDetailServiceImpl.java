package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.dto.response.LoanTransactionDetailResponse;
import com.enigmacamp.loan_app.entity.LoanTransactionDetail;
import com.enigmacamp.loan_app.repository.LoanTransactionDetailRepository;
import com.enigmacamp.loan_app.service.LoanTransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionDetailServiceImpl implements LoanTransactionDetailService {

    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public List<LoanTransactionDetail> createLoanTransactionDetail(List<LoanTransactionDetail> loanTransactionDetailList) {
        return loanTransactionDetailRepository.saveAllAndFlush(loanTransactionDetailList);
    }

    @Override
    public LoanTransactionDetailResponse getLoanTransactionDetailById(String id) {
        LoanTransactionDetail loanTransactionDetail = loanTransactionDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan Transaction Detail Not Found"));
        return convertToResponse(loanTransactionDetail);
    }

    private LoanTransactionDetailResponse convertToResponse(LoanTransactionDetail loanTransactionDetail) {
        return LoanTransactionDetailResponse.builder()
                .id(loanTransactionDetail.getId())
                .transactionDate(loanTransactionDetail.getTransactionDate())
                .nominal(loanTransactionDetail.getNominal())
                .loanStatus(loanTransactionDetail.getLoanStatus())
                .createdAt(loanTransactionDetail.getCreatedAt())
                .updatedAt(loanTransactionDetail.getUpdatedAt())
                .build();
    }
}

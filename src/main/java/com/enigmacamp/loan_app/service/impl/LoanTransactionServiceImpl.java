package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.constant.ApprovalStatus;
import com.enigmacamp.loan_app.constant.LoanStatus;
import com.enigmacamp.loan_app.dto.request.LoanTransactionDetailRequest;
import com.enigmacamp.loan_app.dto.request.LoanTransactionRequest;
import com.enigmacamp.loan_app.dto.response.LoanTransactionDetailResponse;
import com.enigmacamp.loan_app.dto.response.LoanTransactionResponse;
import com.enigmacamp.loan_app.entity.*;
import com.enigmacamp.loan_app.repository.LoanTransactionRepository;
import com.enigmacamp.loan_app.service.LoanTransactionDetailService;
import com.enigmacamp.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeServiceImpl loanTypeService;
    private final InstalmentTypeServiceImpl instalmentTypeService;
    private final CustomerServiceImpl customerService;
    private final LoanTransactionDetailService loanTransactionDetailService;

    @Override
    public LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        LoanType loanType = loanTypeService.getById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentTypeService.getById(request.getInstalmentTypeId());

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .customer(customer)
                .loanType(loanType)
                .instalmentType(instalmentType)
                .createdAt(System.currentTimeMillis())
                .nominal(request.getNominal())
                .approvedAt(System.currentTimeMillis())
                .approvalStatus(ApprovalStatus.APPROVED)
                .approvedBy("Admin")
                .updatedAt(System.currentTimeMillis())
                .build();
        loanTransactionRepository.saveAndFlush(loanTransaction);

//        List<LoanTransactionDetail> loanTransactionDetailList = new ArrayList<>();
//        for (LoanTransactionDetail loanTransactionDetail : loanTransaction.getLoanTransactionDetails()){
//            LoanTransactionDetail loanTransactionDetail1 = LoanTransactionDetail.builder()
//                    .loanStatus(LoanStatus.PAID)
//                    .transactionDate(System.currentTimeMillis())
//                    .loanTransaction(loanTransaction)
//                    .createdAt(System.currentTimeMillis())
//                    .updatedAt(System.currentTimeMillis())
//                    .build();
//            loanTransactionDetailList.add(loanTransactionDetail1);
//        }
//
//        loanTransactionDetailService.createLoanTransactionDetail(loanTransactionDetailList);
//        loanTransaction.setLoanTransactionDetails(loanTransactionDetailList);

        List<LoanTransactionDetail> loanTransactionDetailList = new ArrayList<>();
            LoanTransactionDetail loanTransactionDetail1 = LoanTransactionDetail.builder()
                    .loanStatus(LoanStatus.UNPAID)
                    .transactionDate(System.currentTimeMillis())
                    .loanTransaction(loanTransaction)
                    .createdAt(System.currentTimeMillis())
                    .updatedAt(System.currentTimeMillis())
                    .build();
            loanTransactionDetailList.add(loanTransactionDetail1);
        loanTransaction.setLoanTransactionDetails(loanTransactionDetailList);
        loanTransactionRepository.saveAndFlush(loanTransaction);

        List<LoanTransactionDetailResponse> loanTransactionDetailResponseList = loanTransaction.getLoanTransactionDetails().stream().map(LoanTransactionDetail -> {
            return LoanTransactionDetailResponse.builder()
                    .id(LoanTransactionDetail.getId())
                    .transactionDate(LoanTransactionDetail.getTransactionDate())
                    .nominal(LoanTransactionDetail.getNominal())
                    .loanStatus(LoanTransactionDetail.getLoanStatus())
                    .createdAt(LoanTransactionDetail.getCreatedAt())
                    .updatedAt(LoanTransactionDetail.getUpdatedAt())
                    .build();
        }).toList();

        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .customer(customer.getFirstName() + " " + customer.getLastName())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .loanTransactionDetailResponses(loanTransactionDetailResponseList)
                .build();
    }

    @Override
    public List<LoanTransactionResponse> getAllLoanTransaction() {
        List<LoanTransaction> loanTransactions = loanTransactionRepository.findAll();

        List<LoanTransactionResponse> loanTransactionResponses = new ArrayList<>();
        for (LoanTransaction loanTransaction : loanTransactions){
            LoanTransactionResponse loanTransactionResponse = convertToLoanTransactionResponse(loanTransaction);
            loanTransactionResponses.add(loanTransactionResponse);
        }
        return loanTransactionResponses;
    }

    @Override
    public LoanTransactionResponse getLoanTransactionById(String id) {
        return null;
    }

    private LoanTransactionResponse convertToLoanTransactionResponse(LoanTransaction loanTransaction){
        List<LoanTransactionDetailResponse> loanTransactionDetailResponses = loanTransaction.getLoanTransactionDetails()
                .stream().map(LoanTransactionDetail -> LoanTransactionDetailResponse.builder()
                        .transactionDate(LoanTransactionDetail.getTransactionDate())
                        .nominal(LoanTransactionDetail.getNominal())
                        .loanStatus(LoanTransactionDetail.getLoanStatus())
                        .createdAt(LoanTransactionDetail.getCreatedAt())
                        .updatedAt(LoanTransactionDetail.getUpdatedAt())
                        .build())
                .toList();
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .customer(loanTransaction.getCustomer().getFirstName() + " " + loanTransaction.getCustomer().getLastName())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .loanTransactionDetailResponses(loanTransactionDetailResponses)
                .build();
    }
}

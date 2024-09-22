package com.enigmacamp.loan_app.controller;

import com.enigmacamp.loan_app.dto.request.LoanTransactionRequest;
import com.enigmacamp.loan_app.dto.response.CommonResponse;
import com.enigmacamp.loan_app.dto.response.LoanTransactionResponse;
import com.enigmacamp.loan_app.entity.LoanTransactionDetail;
import com.enigmacamp.loan_app.service.LoanTransactionDetailService;
import com.enigmacamp.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class LoanTransactionController {

    private final LoanTransactionService loanTransactionService;
    private final LoanTransactionDetailService loanTransactionDetailService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createLoanTransaction(@RequestBody LoanTransactionRequest request) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.createLoanTransaction(request);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Successfully created loan transaction")
                .statusCode(HttpStatus.CREATED.value())
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

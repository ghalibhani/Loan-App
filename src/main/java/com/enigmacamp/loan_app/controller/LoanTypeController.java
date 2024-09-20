package com.enigmacamp.loan_app.controller;

import com.enigmacamp.loan_app.dto.request.LoanTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.loan_app.dto.response.CommonResponse;
import com.enigmacamp.loan_app.dto.response.LoanTypeResponse;
import com.enigmacamp.loan_app.entity.LoanType;
import com.enigmacamp.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-types")
@RequiredArgsConstructor
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PostMapping
    public ResponseEntity<?> createLoanType(@RequestBody LoanTypeRequest request) {
        LoanTypeResponse loanTypeRequest = loanTypeService.createLoanType(request);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully created loan type")
                .statusCode(201)
                .data(loanTypeRequest)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanTypeById(@PathVariable String id) {
        LoanTypeResponse loanTypeResponse = loanTypeService.getLoanTypeById(id);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully get loan type by id")
                .statusCode(200)
                .data(loanTypeResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanType() {
        List<LoanType> loanTypeList = loanTypeService.getAllLoanType();
        CommonResponse<List<LoanType>> commonResponse = CommonResponse.<List<LoanType>>builder()
                .message("Successfully get all loan type")
                .statusCode(200)
                .data(loanTypeList)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }

   @PutMapping
    public ResponseEntity<?> updateLoanType(@RequestBody UpdateLoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.updateLoanType(request);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully updated loan type")
                .statusCode(200)
                .data(loanTypeResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanTypeById(@PathVariable String id) {
        loanTypeService.deleteLoanType(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .message("Successfully delete loan type")
                .statusCode(200)
                .data("Successfully delete loan type")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }
}

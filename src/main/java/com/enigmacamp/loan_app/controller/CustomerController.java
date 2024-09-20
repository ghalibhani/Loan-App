package com.enigmacamp.loan_app.controller;

import com.enigmacamp.loan_app.dto.request.CustomerRequest;
import com.enigmacamp.loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.loan_app.dto.response.CommonResponse;
import com.enigmacamp.loan_app.dto.response.CustomerResponse;
import com.enigmacamp.loan_app.entity.Customer;
import com.enigmacamp.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse customerResponse = customerService.createCustomer(request);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully created new customer")
                .statusCode(HttpStatus.CREATED.value())
                .data(customerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully get customer by id")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<Customer> customerList = customerService.getAllCustomer();
        CommonResponse<List<Customer>> commonResponse = CommonResponse.<List<Customer>>builder()
                .message("Successfully get all customer")
                .statusCode(HttpStatus.OK.value())
                .data(customerList)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse customerResponse = customerService.updateCustomer(request);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully updated customer")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .message("Successfully deleted customer")
                .statusCode(HttpStatus.OK.value())
                .data("Successfully deleted customer")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}

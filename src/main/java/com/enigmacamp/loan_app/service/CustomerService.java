package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.request.CustomerRequest;
import com.enigmacamp.loan_app.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomer(CustomerRequest request);
    void deleteCustomer(String id);
}

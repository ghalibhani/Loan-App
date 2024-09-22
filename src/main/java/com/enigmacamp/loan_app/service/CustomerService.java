package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.request.CustomerRequest;
import com.enigmacamp.loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.loan_app.dto.response.CustomerResponse;
import com.enigmacamp.loan_app.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse createCustomer(Customer customer);
    CustomerResponse getCustomerById(String id);
    Customer getById(String id);
    List<Customer> getAllCustomer();
    CustomerResponse updateCustomer(UpdateCustomerRequest request);
    void deleteCustomerById(String id);
}

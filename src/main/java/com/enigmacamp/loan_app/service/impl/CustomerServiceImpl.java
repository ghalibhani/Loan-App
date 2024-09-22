package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.dto.request.CustomerRequest;
import com.enigmacamp.loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.loan_app.dto.response.CustomerResponse;
import com.enigmacamp.loan_app.entity.Customer;
import com.enigmacamp.loan_app.repository.CustomerRepository;
import com.enigmacamp.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        try {
            Customer customer = (Customer.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .dateOfBirth(request.getDateOfBirth())
                    .status(request.getStatus())
                    .build());
            customerRepository.saveAndFlush(customer);
            return convertToCustomerResponse(customer);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exist");
        }
    }

    @Override
    public CustomerResponse createCustomer(Customer request) {
        Customer customer = customerRepository.saveAndFlush(request);
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        return convertToCustomerResponse(customer);
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponse updateCustomer(UpdateCustomerRequest request) {
        try {
            Customer customer = findByIdOrThrowNotFound(request.getId());
            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setPhone(request.getPhone());
            customer.setDateOfBirth(request.getDateOfBirth());
            customer.setStatus(request.getStatus());
            customerRepository.saveAndFlush(customer);
            return convertToCustomerResponse(customer);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exist");
        }
    }

    @Override
    public void deleteCustomerById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .dateOfBirth(customer.getDateOfBirth())
                .status(customer.getStatus())
                .build();
    }
}

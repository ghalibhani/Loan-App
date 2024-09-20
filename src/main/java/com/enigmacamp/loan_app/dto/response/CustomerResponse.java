package com.enigmacamp.loan_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String customerId;

    private String firstName;

    private String lastName;

    private String phone;

    private Date dateOfBirth;

    private String status;
}

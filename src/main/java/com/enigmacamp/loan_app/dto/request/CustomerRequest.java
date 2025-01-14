package com.enigmacamp.loan_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank(message = "Name cannot be blank")
    private String firstName;

    @NotBlank(message = "Name cannot be blank")
    private String lastName;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 11, max = 15, message = "Phone number must be between 11 and 15 characters")
    private String phone;

    private Date dateOfBirth;

    private String status;
}

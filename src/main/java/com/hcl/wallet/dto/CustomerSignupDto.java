package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignupDto {
    @NotNull
    private Long customerId;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String password;
}


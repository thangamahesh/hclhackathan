package com.hcl.wallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignupDTO {

    @NotBlank(message = "Full name should not be blank")
    @Size(min = 4, max = 150, message = "Name should contain 4 to 150 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Special characters & numbers are not allowed in name")
    private String fullName;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Please enter a valid email id")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Mobile number should not be blank")
    @Pattern(regexp = "^\\+?[0-9]{6,15}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, max = 128, message = "Password should be at least 8 characters")
    private String password;

    // Optional: transactional currency id if client wishes to set it during signup
    // private Long transactionalCurrencyId;
}

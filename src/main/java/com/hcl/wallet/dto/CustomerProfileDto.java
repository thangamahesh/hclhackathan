package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileDto {
    private Long customerId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String kycStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


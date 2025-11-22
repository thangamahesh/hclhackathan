package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantSignupDto {

    @NotNull
    private Long merchantId;

    @NotBlank
    private String businessName;

    private String contactEmail;

    private String contactPhone;

    @NotBlank
    private String merchantAccountNumber;

    @NotBlank
    private String settlementCurrency;

    @NotBlank
    private String password;
}

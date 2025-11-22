package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProfileUpdateDto {

    @NotBlank
    private String businessName;

    private String contactEmail;

    private String contactPhone;

    private String settlementCurrency;
}

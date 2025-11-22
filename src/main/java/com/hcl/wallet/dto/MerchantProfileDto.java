package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProfileDto {
    private Long merchantId;
    private String businessName;
    private String contactEmail;
    private String contactPhone;
    private String merchantAccountNumber;
    private String settlementCurrency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

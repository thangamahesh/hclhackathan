package com.hcl.wallet.dto.wallet;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateWalletRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long walletId;

    @NotBlank
    private String currencyCode;

    @DecimalMin(value = "0")
    private BigDecimal initialBalance;
}


package com.hcl.wallet.dto.wallet;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawRequest {
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    private String initiator;
}


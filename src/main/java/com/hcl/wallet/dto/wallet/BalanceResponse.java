package com.hcl.wallet.dto.wallet;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceResponse {
    private BigDecimal balance;
    private String currencyCode;
}


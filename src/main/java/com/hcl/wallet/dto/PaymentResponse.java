package com.hcl.wallet.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {
    private String status;
    private String message;
    private Long transactionId;
}


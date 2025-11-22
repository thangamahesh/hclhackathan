package com.hcl.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Long customerId;
    private Long productId;
    private Long quantity;
}


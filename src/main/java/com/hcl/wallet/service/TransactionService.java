package com.hcl.wallet.service;

import com.hcl.wallet.dto.PaymentRequest;
import com.hcl.wallet.dto.PaymentResponse;

public interface TransactionService {
    PaymentResponse processPayment(PaymentRequest request);
}


package com.hcl.wallet.service;

import com.hcl.wallet.dto.wallet.*;

import java.math.BigDecimal;

public interface WalletService {

    WalletResponse createWallet(CreateWalletRequest request);

    WalletResponse getWallet(Long walletId);

    WalletResponse getWalletByCustomer(Long customerId);

    BalanceResponse getBalance(Long walletId);

    WalletResponse deposit(Long walletId, DepositRequest request);

    WalletResponse withdraw(Long walletId, WithdrawRequest request);

    TransferResponse transfer(TransferRequest request);
}


package com.hcl.wallet.service;

import com.hcl.wallet.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet createWallet(Wallet wallet);

    Optional<Wallet> findById(Long id);

    List<Wallet> getAllWallets();

    Wallet updateWallet(Long id, Wallet wallet);

    void deleteWallet(Long id);
}


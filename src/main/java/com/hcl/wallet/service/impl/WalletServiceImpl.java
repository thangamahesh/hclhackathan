package com.hcl.wallet.service.impl;

import com.hcl.wallet.model.Wallet;
import com.hcl.wallet.repository.WalletRepository;
import com.hcl.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public Wallet createWallet(Wallet wallet) {
        // set created/updated timestamps if not provided
        LocalDateTime now = LocalDateTime.now();
        if (wallet.getCreatedAt() == null) {
            wallet.setCreatedAt(now);
        }
        wallet.setUpdatedAt(now);
        return walletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    @Transactional
    public Wallet updateWallet(Long id, Wallet wallet) {
        return walletRepository.findById(id).map(existing -> {
            // update mutable fields
            existing.setBalanceMinor(wallet.getBalanceMinor());
            existing.setStatus(wallet.getStatus());
            existing.setCurrency(wallet.getCurrency());
            existing.setCustomer(wallet.getCustomer());
            existing.setUpdatedAt(LocalDateTime.now());
            return walletRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    @Transactional
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}


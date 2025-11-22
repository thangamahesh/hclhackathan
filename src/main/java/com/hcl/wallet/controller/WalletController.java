package com.hcl.wallet.controller;

import com.hcl.wallet.dto.wallet.*;
import com.hcl.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@Valid @RequestBody CreateWalletRequest request) {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getWallet(walletId));
    }

    @GetMapping("/user/{customerId}")
    public ResponseEntity<WalletResponse> getWalletByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(walletService.getWalletByCustomer(customerId));
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<WalletResponse> deposit(@PathVariable Long walletId,
                                                   @Valid @RequestBody DepositRequest request) {
        return ResponseEntity.ok(walletService.deposit(walletId, request));
    }

    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<WalletResponse> withdraw(@PathVariable Long walletId,
                                                    @Valid @RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(walletService.withdraw(walletId, request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(walletService.transfer(request));
    }
}


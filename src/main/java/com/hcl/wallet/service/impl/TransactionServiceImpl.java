package com.hcl.wallet.service.impl;

import com.hcl.wallet.dto.PaymentRequest;
import com.hcl.wallet.dto.PaymentResponse;
import com.hcl.wallet.exception.InsufficientBalanceException;
import com.hcl.wallet.exception.InsufficientUnitsException;
import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.model.Products;
import com.hcl.wallet.model.Transactions;
import com.hcl.wallet.model.Wallet;
import com.hcl.wallet.repository.ProductRepository;
import com.hcl.wallet.repository.TransactionsRepository;
import com.hcl.wallet.repository.WalletRepository;
import com.hcl.wallet.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final ProductRepository productRepository;
    private final WalletRepository walletRepository;
    private final TransactionsRepository transactionsRepository;

    public TransactionServiceImpl(ProductRepository productRepository, WalletRepository walletRepository, TransactionsRepository transactionsRepository) {
        this.productRepository = productRepository;
        this.walletRepository = walletRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // 1. Lock product row for update to prevent oversell
        Products product = productRepository.findByIdForUpdate(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        long requestedUnits = request.getQuantity();
        if (product.getUnits() < requestedUnits) {
            throw new InsufficientUnitsException("Not enough units available");
        }

        // 2. Lock customer wallet for update
        Wallet customerWallet = walletRepository.findCustomerWalletForUpdate(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for customer"));

        // 3. Merchant details and settlement currency
        Merchant merchant = product.getMerchant();
        String merchantCurrency = merchant.getSettlementCurrency();
        String productCurrency = product.getCurrency().getCurrencyCode();

        if (!productCurrency.equalsIgnoreCase(merchantCurrency)) {
           // throw new IllegalArgumentException("Product currency and merchant settlement currency do not match");
        }

        // 4. Lock merchant wallet for update
        Wallet merchantWallet = walletRepository.findMerchantWalletForUpdate(merchant.getMerchantId())
                .orElseThrow(() -> new IllegalArgumentException("Merchant wallet not found"));

        // 5. Price calculations (assume product.price is integer minor units stored as string)
        long pricePerUnit;
        try {
            pricePerUnit = Long.parseLong(product.getPrice());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid product price format");
        }

        long totalPrice = pricePerUnit * requestedUnits;

        if (customerWallet.getBalanceMinor() < totalPrice) {
            throw new InsufficientBalanceException("Insufficient balance in customer wallet");
        }

        // 6. All checks passed: perform debit & credit
        customerWallet.setBalanceMinor(customerWallet.getBalanceMinor() - totalPrice);
        merchantWallet.setBalanceMinor(merchantWallet.getBalanceMinor() + totalPrice);

        // decrement product units
        product.setUnits(product.getUnits() - requestedUnits);

        // persist changes
        walletRepository.save(customerWallet);
        walletRepository.save(merchantWallet);
        productRepository.save(product);

        // 7. Create transaction record (let JPA generate id if configured)
        Transactions txn = Transactions.builder()
                .wallet(customerWallet)
                .merchant(merchant)
                .product(product)
                .txnAmount(totalPrice)
                .currency(product.getCurrency())
                .customer(customerWallet.getCustomer())
                .txnType("PAYMENT")
                .status("COMPLETED")
                .initiator("CUSTOMER")
                .createdAt(LocalDateTime.now())
                .completedAt(LocalDateTime.now())
                .build();

        Transactions saved = transactionsRepository.save(txn);

        return PaymentResponse.builder()
                .status("SUCCESS")
                .message("Payment completed")
                .transactionId(saved.getTransactionId())
                .build();
    }
}

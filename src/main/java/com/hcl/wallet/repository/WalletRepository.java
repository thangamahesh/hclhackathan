package com.hcl.wallet.repository;

import com.hcl.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCustomer_CustomerId(Long customerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Wallet w where w.walletId = :id")
    Optional<Wallet> findByIdForUpdate(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Wallet w where w.merchant.merchantId = :merchantId")
    Optional<Wallet> findMerchantWalletForUpdate(@Param("merchantId") Long merchantId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Wallet w where w.customer.customerId = :customerId")
    Optional<Wallet> findCustomerWalletForUpdate(@Param("customerId") Long customerId);
}

package com.hcl.wallet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    private Long walletId;

    @OneToOne(optional = true)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @OneToOne(optional = true)
    @JoinColumn(name = "merchant_id", nullable = true)
    private Merchant merchant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_code")
    private Currency currency;

    @Column(name = "balance_minor", nullable = false)
    private Long balanceMinor;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

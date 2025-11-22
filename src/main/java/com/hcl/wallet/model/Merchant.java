package com.hcl.wallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {

    @Id
    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "business_name", nullable = false, length = 200)
    private String businessName;

    @Column(name = "contact_email", length = 150)
    private String contactEmail;

    @Column(name = "contact_phone", length = 30)
    private String contactPhone;

    @Column(name = "merchant_account_number", nullable = false, length = 100)
    private String merchantAccountNumber;

    @PrimaryKeyJoinColumn(name = "settlement_currency")
    @OneToOne
    private Currency settlementCurrency;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

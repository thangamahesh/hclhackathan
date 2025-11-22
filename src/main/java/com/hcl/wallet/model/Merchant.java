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

    // Use a simple CHAR(3) currency code instead of a Currency entity for now
    @Column(name = "settlement_currency", nullable = false, length = 3)
    private String settlementCurrency;

    // Password hash for merchant authentication (optional column; DB migration required to add this)
    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Explicit getters/setters to ensure correct signatures are available to callers
    public String getSettlementCurrency() {
        return this.settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // Additional explicitly-named accessor to avoid any ambiguities with older signatures
    public String getSettlementCurrencyCode() {
        return this.settlementCurrency;
    }

    public void setSettlementCurrencyCode(String code) {
        this.settlementCurrency = code;
    }

}

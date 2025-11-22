package com.hcl.wallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MerchantSignupDto {

    @NotNull
    private Long merchantId;

    @NotBlank
    private String businessName;

    private String contactEmail;

    private String contactPhone;

    @NotBlank
    private String merchantAccountNumber;

    @NotBlank
    private String settlementCurrency;

    @NotBlank
    private String password;

    // getters / setters
    public Long getMerchantId() { return merchantId; }
    public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getMerchantAccountNumber() { return merchantAccountNumber; }
    public void setMerchantAccountNumber(String merchantAccountNumber) { this.merchantAccountNumber = merchantAccountNumber; }
    public String getSettlementCurrency() { return settlementCurrency; }
    public void setSettlementCurrency(String settlementCurrency) { this.settlementCurrency = settlementCurrency; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}


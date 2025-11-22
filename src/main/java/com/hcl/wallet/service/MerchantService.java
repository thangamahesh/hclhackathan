package com.hcl.wallet.service;

import com.hcl.wallet.dto.MerchantSignupDto;
import com.hcl.wallet.dto.MerchantLoginDto;
import com.hcl.wallet.dto.MerchantProfileUpdateDto;
import com.hcl.wallet.dto.MerchantDashboardDto;
import com.hcl.wallet.model.Merchant;

public interface MerchantService {
    Merchant signup(MerchantSignupDto dto);

    Merchant login(MerchantLoginDto dto);

    Merchant updateProfile(Long merchantId, MerchantProfileUpdateDto dto);

    Merchant getById(Long merchantId);

    Merchant getByAccountNumber(String accountNumber);

    MerchantDashboardDto getDashboard(Long merchantId);
}

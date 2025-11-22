package com.hcl.wallet.service;

import com.hcl.wallet.dto.MerchantSignupDto;
import com.hcl.wallet.model.Merchant;

public interface MerchantService {
    Merchant signup(MerchantSignupDto dto);
}


package com.hcl.wallet.service.impl;

import com.hcl.wallet.dto.MerchantSignupDto;
import com.hcl.wallet.repository.MerchantRepository;
import com.hcl.wallet.service.MerchantService;
import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public Merchant signup(MerchantSignupDto dto) {
        Merchant m = new Merchant();
        m.setMerchantId(dto.getMerchantId());
        m.setBusinessName(dto.getBusinessName());
        m.setContactEmail(dto.getContactEmail());
        m.setContactPhone(dto.getContactPhone());
        m.setMerchantAccountNumber(dto.getMerchantAccountNumber());
        m.setSettlementCurrencyCode(dto.getSettlementCurrency());
        String hashed = PasswordUtil.hashPassword(dto.getPassword());
        m.setPasswordHash(hashed);
        return merchantRepository.save(m);
    }
}

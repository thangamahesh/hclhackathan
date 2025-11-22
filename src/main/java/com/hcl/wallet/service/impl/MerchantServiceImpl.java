package com.hcl.wallet.service.impl;

import com.hcl.wallet.dto.*;
import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.repository.MerchantRepository;
import com.hcl.wallet.service.MerchantService;
import com.hcl.wallet.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

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
        m.setCreatedAt(LocalDateTime.now());
        m.setUpdatedAt(LocalDateTime.now());
        return merchantRepository.save(m);
    }

    @Override
    public Merchant login(MerchantLoginDto dto) {
        Optional<Merchant> opt = merchantRepository.findById(dto.getMerchantId());
        if (opt.isEmpty()) return null;
        Merchant m = opt.get();
        if (!PasswordUtil.verifyPassword(dto.getPassword(), m.getPasswordHash())) {
            return null;
        }
        return m;
    }

    @Override
    public Merchant updateProfile(Long merchantId, MerchantProfileUpdateDto dto) {
        Merchant m = merchantRepository.findById(merchantId).orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
        if (dto.getBusinessName() != null) m.setBusinessName(dto.getBusinessName());
        if (dto.getContactEmail() != null) m.setContactEmail(dto.getContactEmail());
        if (dto.getContactPhone() != null) m.setContactPhone(dto.getContactPhone());
        if (dto.getSettlementCurrency() != null) m.setSettlementCurrencyCode(dto.getSettlementCurrency());
        m.setUpdatedAt(LocalDateTime.now());
        return merchantRepository.save(m);
    }

    @Override
    public Merchant getById(Long merchantId) {
        return merchantRepository.findById(merchantId).orElse(null);
    }

    @Override
    public Merchant getByAccountNumber(String accountNumber) {
        return merchantRepository.findByMerchantAccountNumber(accountNumber).orElse(null);
    }

    @Override
    public MerchantDashboardDto getDashboard(Long merchantId) {
        Optional<Merchant> opt = merchantRepository.findById(merchantId);
        if (opt.isEmpty()) return null;
        Merchant m = opt.get();

        MerchantProfileDto profile = new MerchantProfileDto();
        profile.setMerchantId(m.getMerchantId());
        profile.setBusinessName(m.getBusinessName());
        profile.setContactEmail(m.getContactEmail());
        profile.setContactPhone(m.getContactPhone());
        profile.setMerchantAccountNumber(m.getMerchantAccountNumber());
        profile.setSettlementCurrency(m.getSettlementCurrency());
        profile.setCreatedAt(m.getCreatedAt());
        profile.setUpdatedAt(m.getUpdatedAt());

        MerchantDashboardDto dash = new MerchantDashboardDto();
        dash.setMerchant(profile);
        dash.setCurrentBalance(BigDecimal.ZERO); // placeholder until real ledger exists
        dash.setTransactionsLast7Days(0);
        dash.setAmountLast7Days(BigDecimal.ZERO);
        dash.setPendingSettlements(0);
        dash.setRecentTransactions(new ArrayList<>());
        return dash;
    }

}

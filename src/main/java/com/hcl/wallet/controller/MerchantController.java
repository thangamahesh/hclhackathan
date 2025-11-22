package com.hcl.wallet.controller;

import com.hcl.wallet.dto.MerchantSignupDto;
import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Merchant> signup(@Valid @RequestBody MerchantSignupDto dto) {
        Merchant saved = merchantService.signup(dto);
        return ResponseEntity.ok(saved);
    }
}


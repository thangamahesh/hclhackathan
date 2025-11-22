package com.hcl.wallet.controller;

import com.hcl.wallet.dto.MerchantDashboardDto;
import com.hcl.wallet.dto.MerchantLoginDto;
import com.hcl.wallet.dto.MerchantProfileUpdateDto;
import com.hcl.wallet.dto.MerchantSignupDto;
import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/signup")
    public ResponseEntity<Merchant> signup(@Valid @RequestBody MerchantSignupDto dto) {
        Merchant saved = merchantService.signup(dto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<Merchant> login(@Valid @RequestBody MerchantLoginDto dto) {
        Merchant m = merchantService.login(dto);
        if (m == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(m);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody MerchantProfileUpdateDto dto) {
        try {
            Merchant updated = merchantService.updateProfile(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getById(@PathVariable("id") Long id) {
        Merchant m = merchantService.getById(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<MerchantDashboardDto> getDashboard(@PathVariable("id") Long id) {
        MerchantDashboardDto dashboard = merchantService.getDashboard(id);
        if (dashboard == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dashboard);
    }
}

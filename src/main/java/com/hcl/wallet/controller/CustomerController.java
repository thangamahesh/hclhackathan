package com.hcl.wallet.controller;

import com.hcl.wallet.dto.CustomerLoginDto;
import com.hcl.wallet.dto.CustomerProfileDto;
import com.hcl.wallet.dto.CustomerSignupDto;
import com.hcl.wallet.model.Customer;
import com.hcl.wallet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<CustomerProfileDto> signup(@Valid @RequestBody CustomerSignupDto dto) {
        Customer saved = customerService.signup(dto);
        // return profile DTO
        CustomerProfileDto profile = new CustomerProfileDto();
        profile.setCustomerId(saved.getCustomerId());
        profile.setFullName(saved.getFullName());
        profile.setEmail(saved.getEmail());
        profile.setMobileNumber(saved.getMobileNumber());
        profile.setKycStatus(saved.getKycStatus());
        profile.setCreatedAt(saved.getCreatedAt());
        profile.setUpdatedAt(saved.getUpdatedAt());
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerProfileDto> login(@Valid @RequestBody CustomerLoginDto dto) {
        Customer c = customerService.login(dto);
        if (c == null) return ResponseEntity.status(401).build();
        CustomerProfileDto profile = new CustomerProfileDto();
        profile.setCustomerId(c.getCustomerId());
        profile.setFullName(c.getFullName());
        profile.setEmail(c.getEmail());
        profile.setMobileNumber(c.getMobileNumber());
        profile.setKycStatus(c.getKycStatus());
        profile.setCreatedAt(c.getCreatedAt());
        profile.setUpdatedAt(c.getUpdatedAt());
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileDto> getProfile(@PathVariable("id") Long id) {
        CustomerProfileDto dto = customerService.getProfile(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}


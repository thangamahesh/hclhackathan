package com.hcl.wallet.service.impl;

import com.hcl.wallet.dto.CustomerLoginDto;
import com.hcl.wallet.dto.CustomerProfileDto;
import com.hcl.wallet.dto.CustomerSignupDto;
import com.hcl.wallet.model.Customer;
import com.hcl.wallet.repository.CustomerRepository;
import com.hcl.wallet.service.CustomerService;
import com.hcl.wallet.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer signup(CustomerSignupDto dto) {
        // basic uniqueness checks
        if (customerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (customerRepository.findByMobileNumber(dto.getMobileNumber()).isPresent()) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        Customer c = new Customer();
        c.setCustomerId(dto.getCustomerId());
        c.setFullName(dto.getFullName());
        c.setEmail(dto.getEmail());
        c.setMobileNumber(dto.getMobileNumber());
        c.setKycStatus("NOT_SUBMITTED");
        c.setPasswordHash(PasswordUtil.hashPassword(dto.getPassword()));
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(c);
    }

    @Override
    public Customer login(CustomerLoginDto dto) {
        Optional<Customer> opt = customerRepository.findById(dto.getCustomerId());
        if (opt.isEmpty()) return null;
        Customer c = opt.get();
        if (!PasswordUtil.verifyPassword(dto.getPassword(), c.getPasswordHash())) return null;
        return c;
    }

    @Override
    public CustomerProfileDto getProfile(Long customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty()) return null;
        Customer c = opt.get();
        CustomerProfileDto dto = new CustomerProfileDto();
        dto.setCustomerId(c.getCustomerId());
        dto.setFullName(c.getFullName());
        dto.setEmail(c.getEmail());
        dto.setMobileNumber(c.getMobileNumber());
        dto.setKycStatus(c.getKycStatus());
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUpdatedAt(c.getUpdatedAt());
        return dto;
    }
}


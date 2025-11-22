package com.hcl.wallet.service;

import com.hcl.wallet.dto.CustomerLoginDto;
import com.hcl.wallet.dto.CustomerProfileDto;
import com.hcl.wallet.dto.CustomerSignupDto;
import com.hcl.wallet.model.Customer;

public interface CustomerService {
    Customer signup(CustomerSignupDto dto);
    Customer login(CustomerLoginDto dto);
    CustomerProfileDto getProfile(Long customerId);
}


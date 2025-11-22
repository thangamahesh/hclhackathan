package com.hcl.wallet.service;

import com.hcl.wallet.dto.CustomerSignupDTO;
import com.hcl.wallet.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer signup(CustomerSignupDTO dto);

    Optional<Customer> findById(Long id);

    List<Customer> getAllCustomers();
}


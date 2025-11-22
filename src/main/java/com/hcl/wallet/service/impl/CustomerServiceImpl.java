package com.hcl.wallet.service.impl;

import com.hcl.wallet.dto.CustomerSignupDTO;
import com.hcl.wallet.exception.CustomerAlreadyExistsException;
import com.hcl.wallet.model.Customer;
import com.hcl.wallet.repository.CustomerRepository;
import com.hcl.wallet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer signup(CustomerSignupDTO dto) {
        // check for existing by email or mobile
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer with email already exists");
        }
        if (customerRepository.existsByMobileNumber(dto.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Customer with mobile number already exists");
        }

        Customer customer = new Customer();
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setKycStatus("PENDING");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}


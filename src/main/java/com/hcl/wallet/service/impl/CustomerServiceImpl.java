package com.hcl.wallet.service.impl;

import com.hcl.wallet.dao.CustomerDao;
import com.hcl.wallet.dto.CustomerSignupDTO;

import com.hcl.wallet.model.Customer;
import com.hcl.wallet.repository.CustomerRepository;
import com.hcl.wallet.service.CustomerService;
import com.hclhackathon.exception.CustomerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
    // BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer signup(CustomerSignupDTO dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new CustomerAlreadyExistsException("Email already registered");
        }
        if (customerRepository.existsByMobileNumber(dto.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Mobile number already registered");
        }

        Customer customer = new Customer();
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setKycStatus("PENDING");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        // NOTE: password is stored on User entity; create a linked User
        // Create User and set password hash if necessary. For now, skip user creation

        return customerDao.saveCustomer(customer);
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


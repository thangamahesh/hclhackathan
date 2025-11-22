package com.hcl.wallet.dao;

import com.hcl.wallet.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    List<Customer> getAllCustomers();

    void deleteCustomerById(Long customerId);
}


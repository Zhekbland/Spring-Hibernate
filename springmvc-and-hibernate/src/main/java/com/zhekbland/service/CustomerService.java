package com.zhekbland.service;

import com.zhekbland.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);
}

package com.zhekbland.rest;

import com.zhekbland.entity.Customer;
import com.zhekbland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return this.customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = this.customerService.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        this.customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        this.customerService.saveCustomer(customer);
        return customer;
    }
}
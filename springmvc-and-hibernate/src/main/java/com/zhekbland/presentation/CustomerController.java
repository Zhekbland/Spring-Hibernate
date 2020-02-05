package com.zhekbland.presentation;

import com.zhekbland.dao.CustomerDAO;
import com.zhekbland.model.Customer;
import com.zhekbland.persistence.CustomerDB;
import com.zhekbland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerDB db = CustomerDB.getInstance();

    @PostConstruct
    public void init() {
        this.db.checkTableExist();
    }

    // need to inject the customer dao
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String listCustomers(Model model) {

        // get customers from the service
        List<Customer> customers = this.customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @PreDestroy
    public void destroy() {
        this.db.deleteTable();
    }
}

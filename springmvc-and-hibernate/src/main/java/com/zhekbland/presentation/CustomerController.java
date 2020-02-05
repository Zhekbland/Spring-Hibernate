package com.zhekbland.presentation;

import com.zhekbland.dao.CustomerDAO;
import com.zhekbland.model.Customer;
import com.zhekbland.persistence.CustomerDB;
import com.zhekbland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public String listCustomers(Model model) {

        // get customers from the service
        List<Customer> customers = this.customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    /*
     * We pass customer via model to JSP page
     * for filling our customer through forms (name, email...)
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        this.customerService.saveCustomer(customer);
        return "redirect:/customers/list";
    }

    /*
     *
     */
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
        // get the customer from our service
        Customer customer = this.customerService.getCustomer(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);

        // send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {
        this.customerService.deleteCustomer(id);
        return "redirect:/customers/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @PreDestroy
    public void destroy() {
        this.db.deleteTable();
    }
}

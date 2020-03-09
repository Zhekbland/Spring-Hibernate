package com.zhekbland.springboot.thymeleaf.controller;

import com.zhekbland.springboot.thymeleaf.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees;

    @PostConstruct
    private void loadData() {
        this.employees = new ArrayList<>();
        this.employees.add(new Employee(1, "John", "Vestern", "john@gmail.com"));
        this.employees.add(new Employee(2, "Sarah", "Bear", "sarah@gmail.com"));
        this.employees.add(new Employee(3, "Bob", "Marley", "bob@gmail.com"));
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", this.employees);
        return "list-employees";
    }
}

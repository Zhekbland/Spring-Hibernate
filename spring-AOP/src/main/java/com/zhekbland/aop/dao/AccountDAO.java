package com.zhekbland.aop.dao;

import com.zhekbland.aop.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;

    private String serviceCode;

    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();
        myAccounts.add(new Account("John", "Silver"));
        myAccounts.add(new Account("Madhu", "Platinum"));
        myAccounts.add(new Account("Luca", "Gold"));
        return myAccounts;
    }

    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("No soup for you!!!");
        }
        List<Account> myAccounts = new ArrayList<>();
        myAccounts.add(new Account("John", "Silver"));
        myAccounts.add(new Account("Madhu", "Platinum"));
        myAccounts.add(new Account("Luca", "Gold"));
        return myAccounts;
    }

    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": doing work: adding account");
    }

    public String getName() {
        System.out.println("in getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("in setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("in getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("in setServiceCode");
        this.serviceCode = serviceCode;
    }
}

package com.zhekbland.aop.dao;

import com.zhekbland.aop.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void addAccount(Account account) {
        System.out.println(getClass() + ": doing work: adding account");
    }
}

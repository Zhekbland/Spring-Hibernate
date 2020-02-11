package com.zhekbland.aop;

import com.zhekbland.aop.dao.AccountDAO;
import com.zhekbland.aop.dao.MembershipDAO;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningTest {

    @Test
    public void whenWeTestAOPAfterReturning() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);

        List<Account> accounts = accountDAO.findAccounts();

        System.out.println("\nMain program result: " + accounts);

        context.close();
    }
}
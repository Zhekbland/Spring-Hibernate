package com.zhekbland.aop;

import com.zhekbland.aop.dao.AccountDAO;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingTest {

    @Test
    public void whenWeTestAOPAfterReturning() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);

        List<Account> accounts = null;

        try {
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\nMain program caught exception: " + e);
        }
        System.out.println("Main program result: " + accounts);

        context.close();
    }
}
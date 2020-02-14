package com.zhekbland.aop;

import com.zhekbland.aop.dao.AccountDAO;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterTest {

    @Test
    public void whenWeTestAOPAfterWithException() {
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

    @Test
    public void whenWeTestAOPAfterWithoutException() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);

        List<Account> accounts = null;

        try {
            boolean tripWire = false;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\nMain program caught exception: " + e);
        }
        System.out.println("Main program result: " + accounts);

        context.close();
    }
}
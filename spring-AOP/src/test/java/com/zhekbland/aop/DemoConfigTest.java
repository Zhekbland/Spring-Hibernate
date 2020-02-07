package com.zhekbland.aop;

import com.zhekbland.aop.dao.AccountDAO;
import com.zhekbland.aop.dao.MembershipDAO;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoConfigTest {

    @Test
    public void whenWeTestAOPBefore() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);
        MembershipDAO membershipDAO = context.getBean(MembershipDAO.class);

        Account account = new Account();

        accountDAO.addAccount(account, true);
        membershipDAO.addAccount();
        context.close();
    }
}
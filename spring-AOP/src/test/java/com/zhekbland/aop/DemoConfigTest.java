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

        Account account = new Account("John", "Middle");

        accountDAO.addAccount(account, true);
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");
        membershipDAO.addAccount();
        context.close();
    }
}
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
        accountDAO.addAccount();
        membershipDAO.addAccount();
        context.close();
    }
}
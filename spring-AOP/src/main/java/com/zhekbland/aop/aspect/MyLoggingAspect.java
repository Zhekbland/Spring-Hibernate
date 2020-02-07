package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {
    @Before("execution(public void com.zhekbland.aop.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("Executing @Before advice");
    }
}

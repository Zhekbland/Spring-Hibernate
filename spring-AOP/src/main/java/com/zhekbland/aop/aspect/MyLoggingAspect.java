package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    @Pointcut("execution(* add*(..))")
    private void forDaoPackage() {

    }

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("Executing @Before advice");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("Performing API analytics");
    }
}

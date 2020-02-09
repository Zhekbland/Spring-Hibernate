package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    @Pointcut("execution(* *(..))")
    private void forDaoPackage() {

    }

    @Pointcut("execution(* get*(..))")
    private void getter() {

    }

    @Pointcut("execution(* set*(..))")
    private void setter() {

    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoNoGetterSetter() {

    }

    @Before("forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("Executing @Before advice");
    }

    @Before("forDaoNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("Performing API analytics");
    }
}

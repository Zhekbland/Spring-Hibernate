package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @Before("com.zhekbland.aop.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("Executing @Before advice");
    }

}

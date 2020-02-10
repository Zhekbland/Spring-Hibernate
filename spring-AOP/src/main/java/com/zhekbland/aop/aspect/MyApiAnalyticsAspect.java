package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    @Before("com.zhekbland.aop.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("Performing API analytics");
    }
}

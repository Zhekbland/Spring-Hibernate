package com.zhekbland.aop;

import com.zhekbland.aop.dao.AccountDAO;
import com.zhekbland.aop.service.TrafficFortuneService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AroundTest {

    @Test
    public void whenWeTestAOPAfterWithException() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean(TrafficFortuneService.class);
        String data = fortuneService.getFortune();
        System.out.println("Calling getFortune.");
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
        context.close();
    }
}
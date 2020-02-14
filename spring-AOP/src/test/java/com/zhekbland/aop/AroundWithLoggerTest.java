package com.zhekbland.aop;

import com.zhekbland.aop.service.TrafficFortuneService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class AroundWithLoggerTest {

    private static Logger logger = Logger.getLogger(AroundWithLoggerTest.class.getName());

    @Test
    public void whenWeTestAOPAfterWithException() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean(TrafficFortuneService.class);
        String data = fortuneService.getFortune();
        logger.info("Calling getFortune.");
        logger.info("\nMy fortune is: " + data);
        logger.info("Finished");
        context.close();
    }
}
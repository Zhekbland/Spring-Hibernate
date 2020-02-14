package com.zhekbland.aop;

import com.zhekbland.aop.service.TrafficFortuneService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class AroundHandleExceptionTest {

    private static Logger logger = Logger.getLogger(AroundHandleExceptionTest.class.getName());

    @Test(expected = Exception.class)
    public void whenWeTestAOPAfterWithException() {
        AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
        boolean tripWire = true;
        String data = fortuneService.getFortune(tripWire);
        logger.info("Calling getFortune.");
        logger.info("\nMy fortune is: " + data);
        logger.info("Finished");
        context.close();
    }
}
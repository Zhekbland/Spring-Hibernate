package com.zhekbland.app2;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseballCoachTest {

    @Test
    public void whenWeLookAtBeanLifeCycleInitAndDestroy() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beanLifeCycleApplicationContextXML.xml")) {
            Coach theCoach = context.getBean("baseballCoach", Coach.class);
            theCoach.getDailyFortune();
        }
    }
}
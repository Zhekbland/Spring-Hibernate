package com.zhekbland.app3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BasketballCoachTest {

    @Test
    public void whenWeCreateBeanViaAnnotationAndDoDISetter() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotation.xml")) {
            Coach theCoach = context.getBean("basketballCoach", Coach.class);
            String result = theCoach.getDailyWorkout();
            String resultTwo = theCoach.getDailyFortune();
            assertThat(result, is("Let's play!"));
            assertThat(resultTwo, is("Today is your lucky day!"));
        }
    }
}
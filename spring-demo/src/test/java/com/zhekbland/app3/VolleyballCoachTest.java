package com.zhekbland.app3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VolleyballCoachTest {

    @Test
    public void whenWeCreateBeanViaAnnotationAndDoDIOfField() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotation.xml")) {
            Coach theCoach = context.getBean("volleyballCoach", Coach.class);
            String result = theCoach.getDailyWorkout();
            String resultTwo = theCoach.getDailyFortune();
            assertThat(result, is("Let's play volleyball!"));
            assertThat(resultTwo, is("Today is your lucky day!"));
        }
    }

}
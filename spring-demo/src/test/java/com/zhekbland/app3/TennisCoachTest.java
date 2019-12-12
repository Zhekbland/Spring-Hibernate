package com.zhekbland.app3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TennisCoachTest {

    @Test
    public void whenWeCreateBeanViaAnnotationAndDoDIConstructor() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotation.xml")) {
            Coach theCoach = context.getBean("tennisCoach", Coach.class);
            String result = theCoach.getDailyWorkout();
            String resultTwo = theCoach.getDailyFortune();
            assertThat(result, is("Practice your backhand volley"));
            assertThat(resultTwo, is("Today is your lucky day!"));
        }
    }

}
package com.zhekbland.app5;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoachTest {

    @Test
    public void whenNoXML() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpotConfig.class);
        Coach theCoach = context.getBean("tennisCoach", Coach.class);
        String resultOne = theCoach.getDailyFortune();
        String resultTwo = theCoach.getDailyWorkout();
        assertThat(resultOne, is("Today is your lucky day!"));
        assertThat(resultTwo, is("Practice your backhand volley"));
    }
}
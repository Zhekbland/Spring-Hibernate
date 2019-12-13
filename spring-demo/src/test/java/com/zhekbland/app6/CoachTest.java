package com.zhekbland.app6;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoachTest {

    @Test
    public void whenWeUseBean() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpotConfig.class);
        Coach theCoach = context.getBean("swimCoach", Coach.class);
        String resultOne = theCoach.getDailyFortune();
        String resultTwo = theCoach.getDailyWorkout();
        assertThat(resultOne, is("Today is a sad day."));
        assertThat(resultTwo, is("Swim 1000 meters as a warm up."));
    }

    @Test
    public void whenWeUseBeanWithValueFields() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpotConfig.class);
        SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
        String resultOne = theCoach.getEmail();
        String resultTwo = theCoach.getTeam();
        assertThat(resultOne, is("track@gmail.com"));
        assertThat(resultTwo, is("yanki"));
    }

}
package com.zhekbland.app4;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class CoachTest {

    @Test
    public void whenWeCreatePrototypeBeanAndEqual() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotationScope.xml")) {
            Coach firstCoach = context.getBean("tennisCoach", Coach.class);
            Coach secondCoach = context.getBean("tennisCoach", Coach.class);
            assertNotEquals(firstCoach, secondCoach);
        }
    }

    @Test
    public void whenWeCreateSingletonBeanAndEqual() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotationScope.xml")) {
            Coach firstCoach = context.getBean("basketballCoach", Coach.class);
            Coach secondCoach = context.getBean("basketballCoach", Coach.class);
            assertEquals(firstCoach, secondCoach);
        }
    }

    @Test
    public void whenWeInitAndDestroy() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContextAnnotationScope.xml")) {
            Coach coach = context.getBean("volleyballCoach", Coach.class);
            coach.getDailyFortune();
            coach.getDailyWorkout();
        }
    }

}
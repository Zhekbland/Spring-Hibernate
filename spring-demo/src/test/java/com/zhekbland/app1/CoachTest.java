package com.zhekbland.app1;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoachTest {

    @Test
    public void whenWeRetrieveBeansFromContainerAndDoDIForConstructor() {
        try (ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("applicationContextXML.xml")) {
            Coach theCoach = context.getBean("baseballCoach", Coach.class);
            String resultOne = theCoach.getDailyWorkout();
            String resultTwo = theCoach.getDailyFortune();
            assertThat(resultOne, is("Spend 30 minutes on batting practice"));
            assertThat(resultTwo, is("Today is your lucky day!"));
        }
    }

    @Test
    public void whenWeRetrieveBeansFromContainerAndDoDIForSetter() {
        try (ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("applicationContextXML.xml")) {
            Coach theCoach = context.getBean("cricketCoach", Coach.class);
            String resultOne = theCoach.getDailyWorkout();
            String resultTwo = theCoach.getDailyFortune();
            assertThat(resultOne, is("Practice fast bowling for 15 minutes"));
            assertThat(resultTwo, is("Today is your lucky day!"));
        }
    }

    @Test
    public void whenWeRetrieveBeansFromContainerAndDoDIForValuesInClass() {
        try (ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("applicationContextXML.xml")) {
            CricketCoach theCoach = (CricketCoach) context.getBean("cricketCoach");
            String resultOne = theCoach.getEmailAddress();
            String resultTwo = theCoach.getTeam();
            assertThat(resultOne, is("cricket@gmail.com"));
            assertThat(resultTwo, is("Eagles"));
        }
    }

    @Test
    public void whenWeRetrieveBeansFromContainerAndDoDIForValuesInClassFromOuterFile() {
        try (ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("applicationContextXML.xml")) {
            TrackCoach theCoach = (TrackCoach) context.getBean("trackCoach");
            String resultOne = theCoach.getEmailAddress();
            String resultTwo = theCoach.getTeam();
            assertThat(resultOne, is("track@gmail.com"));
            assertThat(resultTwo, is("yanki"));
        }
    }

}
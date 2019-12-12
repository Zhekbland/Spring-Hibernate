package com.zhekbland.app3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Using @Autowired for Dependency Injection of field with using Reflection API.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2019.
 */
@Component
public class VolleyballCoach implements Coach {

    @Autowired
    @Qualifier("happyFortuneService")
    private FortuneService fortuneService;

    public VolleyballCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Let's play volleyball!";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}

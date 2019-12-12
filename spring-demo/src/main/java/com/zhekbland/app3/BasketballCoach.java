package com.zhekbland.app3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Using @Autowired for Dependency Injection of setter method.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2019.
 */
@Component
public class BasketballCoach implements Coach {
    private FortuneService fortuneService;

    public BasketballCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Let's play!";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }

    @Autowired
    @Qualifier("happyFortuneService")
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
}

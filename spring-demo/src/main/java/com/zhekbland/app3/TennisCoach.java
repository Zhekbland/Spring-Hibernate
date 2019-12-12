package com.zhekbland.app3;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Using @Autowired for Dependency Injection of constructor.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2019.
 */
@Component
public class TennisCoach implements Coach {

	private FortuneService fortuneService;
	
	@Autowired
	public TennisCoach(@Qualifier("happyFortuneService") FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}

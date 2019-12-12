package com.zhekbland.app1;

public class BaseballCoach implements Coach {
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	private FortuneService fortuneService;

	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyFortune() {
		return this.fortuneService.getFortune();
	}

}









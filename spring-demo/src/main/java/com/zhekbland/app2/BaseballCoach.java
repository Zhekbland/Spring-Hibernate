package com.zhekbland.app2;

public class BaseballCoach implements Coach {

	@Override
	public void initMethod() {
		System.out.println("init");
	}

	@Override
	public void destroyMethod() {
		System.out.println("destroy");
	}

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









package com.zhekbland.app1;

public class TrackCoach implements Coach {

	private String emailAddress;
	private String team;

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	private FortuneService fortuneService;

	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyFortune() {
		return this.fortuneService.getFortune();
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}











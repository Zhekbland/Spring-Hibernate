package com.zhekbland.app4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * Using @PostConstruct (init) @PreDestroy (destroy).
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

	// define a default constructor
	public VolleyballCoach() {
	}

	@PostConstruct
	public void init() {
		System.out.println("Init");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destroy");
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

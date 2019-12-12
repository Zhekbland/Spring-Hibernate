package com.zhekbland.app3;

import org.springframework.stereotype.Component;

@Component
public class BadFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Today is your bad day!";
	}

}

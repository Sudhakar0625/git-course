package com.suddhakar.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class Cricket implements Coach {

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Practice daily 15 mins fast bowling!!!";
	}

}

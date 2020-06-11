package com.cap.car.wash.service;

import com.cap.car.wash.model.ScheduleWash;

public interface ScheduleService {
	
	public ScheduleWash createUserProfile(ScheduleWash request);

	public ScheduleWash getUserInfoById(String id);

	public ScheduleWash deleteProfile(String id);

}

package com.cap.car.wash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.car.wash.model.ScheduleWash;

public interface ScheduleWashRepo extends JpaRepository<ScheduleWash, String>{
	
	boolean existsByUserId(String userId);

	Optional<ScheduleWash> findByUserId(String id);

}

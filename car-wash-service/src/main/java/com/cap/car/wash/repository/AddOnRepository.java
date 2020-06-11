package com.cap.car.wash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.car.wash.model.AddOn;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Integer> {

}

package com.cap.car.wash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.car.wash.model.AddOnDescription;



@Repository
public interface AddOnDescRepository extends JpaRepository<AddOnDescription, Integer>{

}

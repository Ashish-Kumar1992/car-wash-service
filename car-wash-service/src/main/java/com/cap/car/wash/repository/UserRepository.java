package com.cap.car.wash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cap.car.wash.model.ForgotPassword;
import com.cap.car.wash.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	User findByEmailAndPassword(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = :pass WHERE u.email = :email")
	public void updateName(String pass, String email); 


	


	
	

}

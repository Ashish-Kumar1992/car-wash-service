package com.cap.car.wash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	/*User findByEmailIdIgnoreCase(String emailId);*/
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = :pass WHERE u.email = :email")
	public void updateName(String pass, String email);

	User findByEmailIgnoreCase(String email);


	void save(CarDetails cardetails);

	Optional<CarDetails> findById(String customercarid);
}

package com.cap.car.wash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.car.wash.model.PromoManagement;


@Repository
public interface PromoRepository extends JpaRepository<PromoManagement, Integer>{

	Optional<PromoManagement> findByPromoName(String promoname);
	
	//Optional<PromoManagement> findByPromoName(String promoname);

}

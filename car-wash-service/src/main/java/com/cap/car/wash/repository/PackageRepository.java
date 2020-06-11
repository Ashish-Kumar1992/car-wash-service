package com.cap.car.wash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.car.wash.model.AddOnDescription;
import com.cap.car.wash.model.Packages;


@Repository
public interface PackageRepository extends JpaRepository<Packages, Long>{

	//List<Packages> findByPackagename();

	//Optional<Packages> findById();

	Optional<Packages> findById(Integer ids);

	//Optional<AddOnDescription> findByPackageId(Integer id);
	
	

}

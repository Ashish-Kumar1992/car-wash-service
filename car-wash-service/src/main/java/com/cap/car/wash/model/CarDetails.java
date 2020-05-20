package com.cap.car.wash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name = " ")
public class CarDetails {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String carname;
	private String profilePicPath;
	
	public CarDetails() {
	
	}

	public CarDetails(Integer id, String carname, String profilePicPath) {
		this.id = id;
		this.carname = carname;
		this.profilePicPath = profilePicPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	@Override
	public String toString() {
		return "CarDetails [id=" + id + ", carname=" + carname + ", profilePicPath=" + profilePicPath + "]";
	}	
}

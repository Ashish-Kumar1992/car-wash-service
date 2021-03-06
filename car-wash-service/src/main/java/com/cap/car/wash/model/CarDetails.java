package com.cap.car.wash.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "car_detail")
public class CarDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long customercarid;
	
	@Column(name = "carname")
	private String carname;
	
	@Column(name = "cartype")
	public String cartype;
	
	@Column(name = "carcolor")
	public String carcolor;
	
	@Column(name = "regnum")
	public String regnum;
	
	@Column(name = "picpath")
	private String profilePicPath;
	
	@Column(name = "createdby")
	private String createdby;
	
	@Column(name = "createddate")
	private LocalDate createdDate;
	
	@Column(name = "updatedby")
	private String updatedby;
	
	@Column(name = "updateddate")
	private LocalDate updatedDate;
	
	
	public CarDetails() {
	
	}


	public CarDetails(Long customercarid, String carname, String cartype, String carcolor, String regnum,
			String profilePicPath, String createdby, LocalDate createdDate, String updatedby, LocalDate updatedDate) {
		this.customercarid = customercarid;
		this.carname = carname;
		this.cartype = cartype;
		this.carcolor = carcolor;
		this.regnum = regnum;
		this.profilePicPath = profilePicPath;
		this.createdby = createdby;
		this.createdDate = createdDate;
		this.updatedby = updatedby;
		this.updatedDate = updatedDate;
	}


	public Long getCustomercarid() {
		return customercarid;
	}


	public void setCustomercarid(Long customercarid) {
		this.customercarid = customercarid;
	}


	public String getCarname() {
		return carname;
	}


	public void setCarname(String carname) {
		this.carname = carname;
	}


	public String getCartype() {
		return cartype;
	}


	public void setCartype(String cartype) {
		this.cartype = cartype;
	}


	public String getCarcolor() {
		return carcolor;
	}


	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}


	public String getRegnum() {
		return regnum;
	}


	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}


	public String getProfilePicPath() {
		return profilePicPath;
	}


	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}


	public String getCreatedby() {
		return createdby;
	}


	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedby() {
		return updatedby;
	}


	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}


	public LocalDate getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}


	@Override
	public String toString() {
		return "CarDetails [customercarid=" + customercarid + ", carname=" + carname + ", cartype=" + cartype
				+ ", carcolor=" + carcolor + ", regnum=" + regnum + ", profilePicPath=" + profilePicPath
				+ ", createdby=" + createdby + ", createdDate=" + createdDate + ", updatedby=" + updatedby
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}

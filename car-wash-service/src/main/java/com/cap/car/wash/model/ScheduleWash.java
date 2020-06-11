package com.cap.car.wash.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="schedule_wash")
public class ScheduleWash {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheduleId")
	private String scheduleId;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "carId")
	private String carId;
	
	@Column(name = "workStatus")
	private String workStatus;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "completionDate")
	private Date completionDate;
	
	@Column(name = "washLocation")
	private String washLocation;

	public ScheduleWash() {

	}

	public ScheduleWash(String scheduleId, String userId, String carId, String workStatus, Date date,
			Date completionDate, String washLocation) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.carId = carId;
		this.workStatus = workStatus;
		this.date = date;
		this.completionDate = completionDate;
		this.washLocation = washLocation;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getWashLocation() {
		return washLocation;
	}

	public void setWashLocation(String washLocation) {
		this.washLocation = washLocation;
	}

	@Override
	public String toString() {
		return "ScheduleWash [scheduleId=" + scheduleId + ", userId=" + userId + ", carId=" + carId + ", workStatus="
				+ workStatus + ", date=" + date + ", completionDate=" + completionDate + ", washLocation="
				+ washLocation + "]";
	}
	
}

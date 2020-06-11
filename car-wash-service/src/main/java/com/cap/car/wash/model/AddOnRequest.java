package com.cap.car.wash.model;

import javax.persistence.Column;

public class AddOnRequest {
	
	private Integer id;
	private Integer descriptionid;
	private Integer Scheduleid;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDescriptionid() {
		return descriptionid;
	}
	public void setDescriptionid(Integer descriptionid) {
		this.descriptionid = descriptionid;
	}
	public Integer getScheduleid() {
		return Scheduleid;
	}
	public void setScheduleid(Integer scheduleid) {
		Scheduleid = scheduleid;
	}
	
	

}

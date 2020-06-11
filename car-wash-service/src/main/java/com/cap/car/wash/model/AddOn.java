package com.cap.car.wash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="add_on")
public class AddOn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descriptionid")
	private Integer descriptionid;
	
	@Column(name = "Scheduleid")
	private Integer Scheduleid;
	
	public AddOn() {
		
	}

	public AddOn(Integer id, Integer descriptionid, Integer scheduleid) {
		this.id = id;
		this.descriptionid = descriptionid;
		Scheduleid = scheduleid;
	}

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

	@Override
	public String toString() {
		return "AddOn [id=" + id + ", descriptionid=" + descriptionid + ", Scheduleid=" + Scheduleid + "]";
	}
	

}

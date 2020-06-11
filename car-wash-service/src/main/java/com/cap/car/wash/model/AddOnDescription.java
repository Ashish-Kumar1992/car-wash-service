package com.cap.car.wash.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="add_on_desc")
public class AddOnDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "packageid")
	private String packageid;
	
	@Column(name = "price")
	private double price;

	public AddOnDescription() {

	}

	public AddOnDescription(int id, String description, String packageid, double price) {
		super();
		this.id = id;
		this.description = description;
		this.packageid = packageid;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AddOnDescription [id=" + id + ", description=" + description + ", packageid=" + packageid + ", price="
				+ price + "]";
	}

	
	

}

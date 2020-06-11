package com.cap.car.wash.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promo_management")
public class PromoManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "promoname")
	private String promoName;
	
	@Column(name = "discountpercentage")
	private Integer discountPercentage;
	
	@Column(name = "minimumamount")
	private Float minimumAmount;
	
	@Column(name = "maximumamount")
	private Float maximumAmount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "createdby")
	private String createdby;
	
	@Column(name = "createddate")
	private LocalDate createdDate;
	
	@Column(name = "updatedby")
	private String updatedby;
	
	@Column(name = "updateddate")
	private LocalDate updatedDate;
	
	
	public PromoManagement() {

	}


	public PromoManagement(Integer id, String promoName, Integer discountPercentage, Float minimumAmount,
			Float maximumAmount, String status, String createdby, LocalDate createdDate, String updatedby,
			LocalDate updatedDate) {
		this.id = id;
		this.promoName = promoName;
		this.discountPercentage = discountPercentage;
		this.minimumAmount = minimumAmount;
		this.maximumAmount = maximumAmount;
		this.status = status;
		this.createdby = createdby;
		this.createdDate = createdDate;
		this.updatedby = updatedby;
		this.updatedDate = updatedDate;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPromoName() {
		return promoName;
	}


	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}


	public Integer getDiscountPercentage() {
		return discountPercentage;
	}


	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}


	public Float getMinimumAmount() {
		return minimumAmount;
	}


	public void setMinimumAmount(Float minimumAmount) {
		this.minimumAmount = minimumAmount;
	}


	public Float getMaximumAmount() {
		return maximumAmount;
	}


	public void setMaximumAmount(Float maximumAmount) {
		this.maximumAmount = maximumAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
		return "PromoManagement [id=" + id + ", promoName=" + promoName + ", discountPercentage=" + discountPercentage
				+ ", minimumAmount=" + minimumAmount + ", maximumAmount=" + maximumAmount + ", status=" + status
				+ ", createdby=" + createdby + ", createdDate=" + createdDate + ", updatedby=" + updatedby
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	
}

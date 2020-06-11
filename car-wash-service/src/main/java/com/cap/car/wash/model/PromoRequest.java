package com.cap.car.wash.model;

import java.time.LocalDate;

import javax.persistence.Column;

public class PromoRequest {
	
	private String promoName;
	private Integer discountPercentage;
	private Float minimumAmount;
	private Float maximumAmount;
	private String status;
	private String createdby;
	private LocalDate createdDate;
	private String updatedby;
	private LocalDate updatedDate;
	
	
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
	

}

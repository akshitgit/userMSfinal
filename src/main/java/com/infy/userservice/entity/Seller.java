package com.infy.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {

	@Id
	@Column(name = "SELLERID", nullable = false)
	private Integer sellerId;
	
	@Column(name="NAME",nullable = false)
	private String name;
	
	@Column(name="EMAIL",nullable = false)
	private String email;
	
	@Column(name = "PHONENUMBER",nullable = false)
	private String phoneNumber;
	
	@Column(name="PASSWORD",nullable = false)
	private String password;
	
	@Column(name="ISACTIVE",length = 1)
	private Integer isActive;

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	
	

}

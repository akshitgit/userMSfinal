package com.infy.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name= "cart")
@IdClass(CartKey.class)
public class Cart {

	@Id
	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	
	@Id
	@Column(name="PRODID",nullable = false)
	private Integer prodId;
	

	@Column(name="Quantity",nullable = false)
	private Integer quantity;
	
	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

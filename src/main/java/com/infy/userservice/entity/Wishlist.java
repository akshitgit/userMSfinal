package com.infy.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
@IdClass(WishlistKey.class)
public class Wishlist {

	@Id
	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	
	@Id
	@Column(name="PRODID",nullable = false)
	private Integer prodId;

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
	
}

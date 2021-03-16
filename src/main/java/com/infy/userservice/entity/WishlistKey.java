package com.infy.userservice.entity;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class WishlistKey implements Serializable{
	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	
	@Column(name="PRODID",nullable = false)
	private Integer prodId;

	public WishlistKey(Integer buyerId, Integer prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
	}

	public WishlistKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}

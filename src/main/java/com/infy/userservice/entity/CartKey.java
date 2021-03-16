package com.infy.userservice.entity;

import java.io.Serializable;

import javax.persistence.Column;



@SuppressWarnings("serial")
public class CartKey implements Serializable {
	

	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	
	
	@Column(name="PRODID",nullable = false)
	private Integer prodId;


	public CartKey(Integer buyerId, Integer prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
	}


	public CartKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	
	
}

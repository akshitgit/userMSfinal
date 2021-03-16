package com.infy.userservice.dto;

import com.infy.userservice.entity.Wishlist;

public class WishlistDTO {
	private Integer buyerId;
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

	public String toString() {
		return "buyerId "+this.prodId+"ProdId "+this.prodId;
	}
	//DTO to entity
	public Wishlist createEntity() {
		Wishlist wishlist=new Wishlist();
		wishlist.setBuyerId(this.buyerId);
		wishlist.setProdId(this.prodId);
		
		return wishlist;
	}
	
	//Entity to DTO
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO=new WishlistDTO();
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
		wishlistDTO.setProdId(wishlist.getProdId());
		
		return wishlistDTO;
	}
}

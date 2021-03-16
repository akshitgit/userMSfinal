package com.infy.userservice.dto;



import com.infy.userservice.entity.Seller;

public class SellerDTO {

	private Integer sellerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
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
	
	public String toString() {
		return "SellerId "+ this.sellerId+"\nname "+this.name+"\nemail "+this.email+"\npassword "+this.password
				+"\nphoneNumber "+this.phoneNumber+"\nisActive "+this.isActive;
	}
	
	//DTO to Entity
	public Seller createEntity() {
		Seller seller= new Seller();
		
		seller.setSellerId(this.sellerId);
		seller.setEmail(this.email);
		seller.setIsActive(this.isActive);
		seller.setName(this.name);
		seller.setPassword(this.password);
		seller.setPhoneNumber(this.phoneNumber);
		return seller;

	}
	
	//Entity to DTO
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO=new SellerDTO();
		sellerDTO.setSellerId(seller.getSellerId());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setIsActive(seller.getIsActive());
		sellerDTO.setName(seller.getName());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setPhoneNumber(seller.getPhoneNumber());
		
		return sellerDTO;
	}
}

package com.infy.userservice.validator;


import com.infy.userservice.dto.BuyerDTO;
import com.infy.userservice.dto.SellerDTO;

public class Validator {
	
	
	public static void validateBuyer(BuyerDTO buyer) throws Exception {
		if(!validateName(buyer.getName()))
			throw new Exception("Validate.INVALID_NAME");
		if(!validateEmail(buyer.getEmail()))
			throw new Exception("Validate.INVALID_EMAIL");
		if(!validatePhoneNumber(buyer.getPhoneNumber()))
			throw new Exception("Validate.INVALID_PHONE");
		if(!validatePassword(buyer.getPassword()))
			throw new Exception("Validate.INVALID_PASSWORD");
	}
	
	public static void validateSeller(SellerDTO seller) throws Exception{
		if(!validateName(seller.getName()))
			throw new Exception("Validate.INVALID_NAME");
		if(!validateEmail(seller.getEmail()))
			throw new Exception("Validate.INVALID_EMAIL");
		if(!validatePhoneNumber(seller.getPhoneNumber()))
			throw new Exception("Validate.INVALID_PHONE");
		if(!validatePassword(seller.getPassword()))
			throw new Exception("Validate.INVALID_PASSWORD");
		
	}
	public static boolean validateName(String name)  {
		String regex="[A-Za-z]{1,}[A-Za-z\\s]{1,}[A-Za-z]{1,}";
		return name.matches(regex);
	}
	
	public static boolean validateEmail(String email){
		String regex="[a-zA-Z0-9+_.-]+[a-zA-Z0-9]+@[a-zA-Z]+\\.com";
		return email.matches(regex);
		
	}
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		String regex="[789]{1}\\d{9}";
		return phoneNumber.matches(regex);
		
	}
	
	public static boolean validatePassword(String password) {
		String regex="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{7,20}";
		return password.matches(regex);
	}
	

}

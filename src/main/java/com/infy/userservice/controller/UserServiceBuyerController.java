package com.infy.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.userservice.dto.BuyerDTO;
import com.infy.userservice.dto.LoginDTO;
import com.infy.userservice.service.UserBuyerService;

@RestController
@CrossOrigin
public class UserServiceBuyerController {

	@Autowired
	private UserBuyerService userBuyerService;
	
	@Autowired
	private Environment environment;
	
	/*****************BUYER RELATED ***************************************/
	
	//Buyer login
	@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		boolean islogin=userBuyerService.login(loginDTO);
		if(islogin) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.BUYER_LOGIN"),HttpStatus.OK);
			return response;
		}
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.WRONG_CREDENTIALS"),HttpStatus.BAD_REQUEST);
		return response;
		
	}
	
	//registering a buyer
	@PostMapping(value = "/buyer/register",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO)throws Exception{
		try {
			userBuyerService.buyerRegistration(buyerDTO);
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.BUYER_REGISTRATION"),HttpStatus.CREATED);
			return response;
		}catch (Exception e) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
			return response;
		}
	}
	
	//get all the buyers
	@GetMapping(value= "/buyer",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BuyerDTO> showBuyers(){
		List<BuyerDTO> buyers=userBuyerService.showBuyers();
		return buyers;
	}
	
	//removal of buyer
	@PutMapping(value="/buyer/{buyerId}")
	public ResponseEntity<String> removeBuyer(@PathVariable Integer buyerId) throws Exception{
		try {
			userBuyerService.removeBuyer(buyerId);
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("Service.BUYER_REMOVAL"),HttpStatus.OK);
			return response;	
		}catch (Exception e) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
			return response;
		}
		
	}
	
	//update reward point
	@PutMapping(value="/buyer/{buyerId}/rewardpoints/{amount}")
	public ResponseEntity<String> updateRewardPoints(@PathVariable Integer buyerId, @PathVariable Double amount){
		if(userBuyerService.updateRewardPoints(buyerId, amount)) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("Service.BUYER_REWARDS_UPDATE"),HttpStatus.OK);
		    return response;
		}
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("Service.BUYER_REWARDS_UPDATE_FAIL"),HttpStatus.OK);
	    return response;
	}
	
	//use reward points for discount
	@GetMapping(value="/buyer/{buyerId}/discount/{amount}")
	public Double useRewardPoints(@PathVariable Integer buyerId,@PathVariable Double amount){
		return userBuyerService.useRewardPoints(buyerId,amount);
		
		
	}
	
	//set isPriviledged
	@PutMapping(value = "/buyer/{buyerId}/privileged")
	public Boolean isPrivileged(@PathVariable Integer buyerId){
		return userBuyerService.isPrivileged(buyerId);
		
	}
	
	//get isPriviledged
	@GetMapping(value="/buyer/{buyerId}/privileged")
	public Boolean getisPrivileged(@PathVariable Integer buyerId) {
		return userBuyerService.getisPrivileged(buyerId);
	}
	
}

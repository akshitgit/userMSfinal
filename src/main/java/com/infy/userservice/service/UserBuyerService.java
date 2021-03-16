package com.infy.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userservice.dto.BuyerDTO;
import com.infy.userservice.dto.LoginDTO;
import com.infy.userservice.entity.Buyer;
import com.infy.userservice.repository.BuyerRepository;
import com.infy.userservice.validator.Validator;

@Service
public class UserBuyerService {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	//Buyer login
	public boolean login(LoginDTO loginDTO) {
		Buyer buyer=buyerRepository.findByEmail(loginDTO.getEmail());
		if(buyer!=null) {
			isPrivileged(buyer.getBuyerId());
			if(buyer.getPassword().equals(loginDTO.getPassword())) 
				return true;
		}
		return false;
	}
	
	//Buyer's registration
	public void buyerRegistration(BuyerDTO buyerDTO)throws Exception {
		Validator.validateBuyer(buyerDTO);
	    Buyer buyer=buyerDTO.createEntity();
	    Buyer existingBuyer=buyerRepository.getByPhoneNumber(buyer.getPhoneNumber());
		if(existingBuyer!=null)
			throw new Exception("Service.BUYER_ALREADY_EXISTS_WITH_NUMBER");
		buyerRepository.save(buyer);
	}
	
	//get list of buyers
	public List<BuyerDTO> showBuyers(){
		List<Buyer> buyersEntities=buyerRepository.findAll();
		List<BuyerDTO> buyers=new ArrayList<>();
		for(Buyer buyer:buyersEntities) {
			buyers.add(BuyerDTO.valueOf(buyer));
		}
		return buyers;
	}
	
	//Removal of Buyer
	public void removeBuyer(Integer buyerId) throws Exception {
		Optional<Buyer> buyer=buyerRepository.findById(buyerId);
		if(buyer.isPresent()) {
			buyer.get().setIsActive(0);
			buyerRepository.save(buyer.get());
		}
		else
			throw new Exception("Service.BUYER_DOES_NOT_EXISTS");
	}
	
	public boolean updateRewardPoints(Integer buyerId,Double amount) {
		if(amount>=100) {
			Integer reward=(int) (amount/100);
			Optional<Buyer> buyer=buyerRepository.findById(buyerId);
			if(buyer.isPresent()) {
				if(buyer.get().getRewardPoints()==null) {
					buyer.get().setRewardPoints(0);
					buyerRepository.save(buyer.get());
				}
				reward+=buyer.get().getRewardPoints();
				buyer.get().setRewardPoints(reward);
				buyerRepository.save(buyer.get());
				return true;
			}
		}
		return false;
	}
	
	public Double useRewardPoints(Integer buyerId, Double amount) {
		Optional<Buyer> buyer=buyerRepository.findById(buyerId);
		Double amountDiscounted=0.0;
		if(buyer.isPresent()) {
			if(buyer.get().getRewardPoints()==null || buyer.get().getRewardPoints()==0) {
				return 0.0;
			}
			amountDiscounted=buyer.get().getRewardPoints()/4.0;
			if(amountDiscounted>amount) {
				amountDiscounted=amount;
				Integer rewardRemaining=(int) (buyer.get().getRewardPoints()-(amount*4));
				buyer.get().setRewardPoints(rewardRemaining);
				
				
			}else {
				buyer.get().setRewardPoints(0);
			}
			buyerRepository.save(buyer.get());
			
		}
		return amountDiscounted;
	}
	
	public Boolean isPrivileged(Integer buyerId) {
		Optional<Buyer> buyer=buyerRepository.findById(buyerId);
		if(buyer.isPresent()) {
			Integer rewardPoint=buyer.get().getRewardPoints();
			if(rewardPoint==null) {
				buyer.get().setIsPrivileged(0);
			}
			else if(buyer.get().getRewardPoints()>10000) {
				buyer.get().setIsPrivileged(1);
				buyerRepository.save(buyer.get());
				return true;
				
			}else {
				buyer.get().setIsPrivileged(0);
			}
			
			buyerRepository.save(buyer.get());
			
		}
		return false;
	}
	
	public Boolean getisPrivileged(Integer buyerId) {
		Optional<Buyer> buyer=buyerRepository.findById(buyerId);
		if(buyer.isPresent()) {
			if(buyer.get().getIsPrivileged()==null) {
				return false;
			}
			else if(buyer.get().getIsPrivileged()==1) {
				return true;
			}
		}
		return false;
	}

}

package com.infy.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userservice.dto.LoginDTO;
import com.infy.userservice.dto.SellerDTO;
import com.infy.userservice.entity.Seller;
import com.infy.userservice.repository.SellerRepository;
import com.infy.userservice.validator.Validator;

@Service
public class UserSellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	//Buyer login
	public boolean login(LoginDTO loginDTO) {
		Seller seller=sellerRepository.findByEmail(loginDTO.getEmail());
		if(seller!=null) {
			if(seller.getPassword().equals(loginDTO.getPassword())) 
				return true;
			}
		return false;
	}
	
	//Seller's registration
	public void sellerRegistration(SellerDTO sellerDTO)throws Exception {
		Validator.validateSeller(sellerDTO);
		Seller seller=sellerDTO.createEntity();
		seller.setIsActive(1);
		Seller existingBuyer=sellerRepository.getByPhoneNumber(seller.getPhoneNumber());
		if(existingBuyer!=null)
			throw new Exception("Service.SELLER_ALREADY_EXISTS_WITH_NUMBER");
		sellerRepository.save(seller);
	}
	
	//get list of seller's
	public List<SellerDTO> showSellers(){
		List<Seller> sellersEntities=sellerRepository.findAll();
		List<SellerDTO> sellers=new ArrayList<>();
		for(Seller seller:sellersEntities) {
			sellers.add(SellerDTO.valueOf(seller));
		}
		return sellers;
		}
	//Removal of seller
	public void removeSeller(Integer sellerId) throws Exception {
		Optional<Seller> seller=sellerRepository.findById(sellerId);
			if(seller.isPresent()) {
				seller.get().setIsActive(0);
				sellerRepository.save(seller.get());
			}
			else
				throw new Exception("Service.SELLER_DOES_NOT_EXISTS");
		}

}

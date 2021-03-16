package com.infy.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userservice.entity.Buyer;
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>{
	public Buyer getByPhoneNumber(String phoneNumber);
	public Buyer findByEmail(String email);

}

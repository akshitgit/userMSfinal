package com.infy.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userservice.entity.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
	public Seller getByPhoneNumber(String phoneNumber);
	public Seller findByEmail(String email);

}

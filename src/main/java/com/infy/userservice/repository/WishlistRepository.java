package com.infy.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userservice.entity.Wishlist;
import com.infy.userservice.entity.WishlistKey;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, WishlistKey>{

}

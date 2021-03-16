package com.infy.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.userservice.dto.CartDTO;
import com.infy.userservice.dto.WishlistDTO;

import com.infy.userservice.entity.Cart;
import com.infy.userservice.entity.CartKey;
import com.infy.userservice.entity.Wishlist;
import com.infy.userservice.entity.WishlistKey;

import com.infy.userservice.repository.CartRepository;
import com.infy.userservice.repository.WishlistRepository;


@Service
public class UserService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	

/*****************ORDER RELATED ***************************************/	
	
	//move from wishList to cart or adding product to cart
	public void addToCart(CartDTO cartDTO) {
		
		//adding to cart
		Cart cart=cartDTO.createEntity();
		cartRepository.save(cart);
		
		WishlistKey wishlistKey=new WishlistKey(cart.getBuyerId(), cart.getProdId());
		
		//removing from wishList
		Wishlist wishlist=wishlistRepository.getOne(wishlistKey);
		
		if(wishlist!=null)
			wishlistRepository.delete(wishlist);
	
	}
	
	//move from cart to wishList
	public void addToWishlist(WishlistDTO wishlistDTO) {
		Wishlist wishlist=wishlistDTO.createEntity();
		wishlistRepository.save(wishlist);
		
		CartKey cartKey=new CartKey(wishlist.getBuyerId(),wishlist.getProdId());
		Cart cart=cartRepository.getOne(cartKey);
		
		if(cart!=null)
			cartRepository.delete(cart);
		
	}
	
	//remove particular product from cart
	public void removeFromCart(CartDTO cartDTO) {
		Cart cart =cartDTO.createEntity();
		cartRepository.delete(cart);
	}
	
	//checkout from cart for particular buyer
	public void checkOutFromCart(Integer buyerId) {
		List<Cart> cartList=cartRepository.getByBuyerId(buyerId);
		for(Cart item:cartList) {
			cartRepository.delete(item);
		}
		
	}
	
	//show cart for particular buyer
	public List<CartDTO> showCart(Integer buyerId) {
		List<Cart> cartList=cartRepository.getByBuyerId(buyerId);
		List<CartDTO> cartDTOList=new ArrayList<>();
		for(Cart item:cartList) {
			cartDTOList.add(CartDTO.valueOf(item));
		}
		return cartDTOList;
	}
	

}

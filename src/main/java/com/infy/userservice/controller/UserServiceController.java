package com.infy.userservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.infy.userservice.dto.CartDTO;
import com.infy.userservice.dto.WishlistDTO;
import com.infy.userservice.service.UserService;

@RestController
@CrossOrigin
public class UserServiceController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	
	/*****************ORDER RELATED ***************************************/
	
	//move from wishList to cart or adding product to cart
	@PostMapping(value = "/cart",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
		userService.addToCart(cartDTO);
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.ADDING_CART_SUCCESSFUL"),HttpStatus.OK);
		return response;
	}
	
	//move from cart to wishList or add product to wishList
	@PostMapping(value = "/wishlist",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		userService.addToWishlist(wishlistDTO);
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.ADDING_WISHLIST_SUCCESSFUL"),HttpStatus.OK);
		return response;
	}
	
	//remove particular product from cart
	@DeleteMapping(value = "/cart")
	public ResponseEntity<String> removeFromCart(@RequestBody CartDTO cartDTO) {
		userService.removeFromCart(cartDTO);
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.REMOVE_CART_SUCCESSFUL"),HttpStatus.OK);
		return response;
	}
	
	//checkout from cart for particular buyer
	@DeleteMapping(value = "/cart/{buyerId}")
	public void checkOutFromCart(@PathVariable Integer buyerId) {
		userService.checkOutFromCart(buyerId);
	}
	
	//show cart for particular buyer
	@GetMapping(value= "/cart/{buyerId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> showCart(@PathVariable Integer buyerId){
		List<CartDTO> cartList=userService.showCart(buyerId);
		return cartList;
		
	}

}

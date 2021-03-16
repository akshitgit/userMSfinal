package com.infy.userservice.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import com.infy.userservice.dto.LoginDTO;
import com.infy.userservice.dto.SellerDTO;
import com.infy.userservice.service.UserSellerService;

@RestController
@CrossOrigin
public class UserServiceSellerController {
	
	@Autowired
	private UserSellerService UserSellerService;
	
	@Autowired
	private Environment environment;
	
	@Value("${Product.uri}")
	String productUri;
	
	/*****************SELLER RELATED ***************************************/
	
	//Buyer login
	@PostMapping(value = "/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		boolean islogin=UserSellerService.login(loginDTO);
		if(islogin) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.SELLER_LOGIN"),HttpStatus.OK);
			return response;
		}
		ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.WRONG_CREDENTIALS"),HttpStatus.BAD_REQUEST);
		return response;
			
		}
	
	//registering a seller
	@PostMapping(value = "/seller/register",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDTO)throws Exception{
		try {
			UserSellerService.sellerRegistration(sellerDTO);
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("API.SELLER_REGISTRATION"),HttpStatus.CREATED);
			return response;
		}catch (Exception e) {
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
			return response;
			}
	}
	
	//get all the sellers
	@GetMapping(value= "/seller",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SellerDTO> showSellers(){
		List<SellerDTO> sellers=UserSellerService.showSellers();
		return sellers;
	}
	
	//removal of seller
	@PutMapping(value="/seller/{sellerId}")
	public ResponseEntity<String> removeSeller(@PathVariable Integer sellerId) throws Exception{
		try {
			UserSellerService.removeSeller(sellerId);
			String url="http://localhost:8100/api/products/seller/{sellerId}";
			RestTemplate restTemplate=new RestTemplate();
			restTemplate.delete(url,sellerId);
			System.out.println("deleted");
			ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty("Service.SELLER_REMOVAL"),HttpStatus.OK);
			return response;	
			}catch (Exception e) {
				ResponseEntity<String> response= new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
				return response;
			}
			
		}

}

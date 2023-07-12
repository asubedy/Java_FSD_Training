package com.SportyShoes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SportyShoes.entities.Products;
import com.SportyShoes.entities.Transaction;
import com.SportyShoes.entities.User;
import com.SportyShoes.repository.ProductRepo;
import com.SportyShoes.repository.TransactionRepo;
import com.SportyShoes.repository.UserRepo;
import com.SportyShoes.utils.TokenGenerator;

@RestController
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired 
	TransactionRepo transactionRepo;
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User userDetails) {
		try {
			 userDetails.setRole("ROLE_USER");

		     User savedUser = userRepo.save(userDetails);
		     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<String> loginUser(@RequestBody User userDetails) {
		
		String username = userDetails.getUserName();
		String passWord = userDetails.getPassword();
		System.out.println(username);
		System.out.println(passWord);
		List<User> fetchUser = userRepo.findByUserNameAndPassword(username, passWord);
		String role = fetchUser.get(0).getRole();
		 if ((!fetchUser.isEmpty()) && ("ROLE_USER".equals(role))) {
			 User user = fetchUser.get(0);
	            // Generate and set the token for the user
	            String token = TokenGenerator.generateToken(); 
	            user.setToken(token);
	            userRepo.save(user);
	            return new ResponseEntity<>("User Successfully Logged in with token: "+token, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	        }
	}
	
	@PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam  String token) {
        Optional<User> optionalUser = userRepo.findByToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Delete the token
            user.setToken(null);
            userRepo.save(user);
            return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
	}
	
	@PostMapping("/buy/{productId}")
	public String buyProduct(@PathVariable int productId, @RequestParam String userName) {
		
		Optional<Products> products= productRepo.findById(productId);
		if(products.isPresent()) {
			
			Products product = products.get();
			Transaction trn = new Transaction();
			
			trn.setCategoryName(product.getCategory().getCat_name());
			trn.setCid(product.getCategory().getCat_id());
			trn.setPid(product.getProd_id());
			trn.setProductName(product.getProd_name());
			trn.setUserName(userName);
			
			transactionRepo.save(trn);
			return "Product bought";
		}
		else {
			return "Product does not exist";
		}
		
		
		
		
	}
	
	
}

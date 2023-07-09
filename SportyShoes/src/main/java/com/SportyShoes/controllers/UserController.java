package com.SportyShoes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/allUser")
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User userDetails) {
		try {
			 userDetails.setRole("ROLE_USER");

		     User savedUser = userRepo.save(userDetails);
		     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//			User _userDetails = userRepo.save(new User(userDetails.getU_id(),userDetails.getUserName(),userDetails.getUserEmail(),userDetails.getPassword(),userDetails.getRole()));
//			return new ResponseEntity<>(_userDetails, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<String> loginUser(@RequestBody User userDetails) {
		
		String username = userDetails.getUserName();
		String passWord = userDetails.getPassword();
		
		// Check if there is already a logged-in user
//	    Optional<User> optionalLoggedInUser = userRepo.findByTokenNotNull();
//	    User loggedInUser = optionalLoggedInUser.get();
//	    if (optionalLoggedInUser.isPresent()&&loggedInUser.getRole()!="") {
//	        if (loggedInUser.getUserName().equals(username)) {
//	            return new ResponseEntity<>("You are already logged in.", HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>("Another user is already logged in. Logout First", HttpStatus.CONFLICT);
//	        }
//	    }
		
		List<User> fetchUser = userRepo.findByUserNameAndPassword(username, passWord);
		User user = fetchUser.get(0);
		 if (!fetchUser.isEmpty() && user.getRole()=="ROLE_USER") {
	            
	            // Generate and set the token for the user
	            String token = TokenGenerator.generateToken(); 
	            user.setToken(token);
	            userRepo.save(user);
	            return new ResponseEntity<>(token, HttpStatus.OK);
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
		System.out.println(products.get());
//		Products [prod_id=52, prod_name=aleepss, price=0.0, category=Categories [cat_id=1, cat_name=basketball]]
		System.out.println("Prod id "+products.get().getProd_id());
		System.out.println("Cat namee "+products.get().getCategory().getCat_name());
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

package com.SportyShoes.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SportyShoes.entities.Categories;
import com.SportyShoes.entities.ProductDTO;
import com.SportyShoes.entities.Products;
import com.SportyShoes.entities.Transaction;
import com.SportyShoes.entities.User;
import com.SportyShoes.repository.CategoryRepo;
import com.SportyShoes.repository.ProductRepo;
import com.SportyShoes.repository.TransactionRepo;
import com.SportyShoes.repository.UserRepo;
import com.SportyShoes.service.CategoryService;
import com.SportyShoes.utils.TokenGenerator;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryRepo adminCatRepo;
	
	@Autowired
	ProductRepo adminProdRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	TransactionRepo transactionRepo;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestBody User userDetails){
		String userNameString = userDetails.getUserName();
		String userPasswordString = userDetails.getPassword();
		
		List<User> fetchedUser=userRepo.findByUserNameAndPassword(userNameString, userPasswordString);
		User adminUser = fetchedUser.get(0);
		if(!fetchedUser.isEmpty()&& adminUser.getRole()!="ROLE_ADMIN") {
			String token = TokenGenerator.generateToken();
			adminUser.setToken(token);
			userRepo.save(adminUser);
			return new ResponseEntity<>("Admin Successfully Logged in with token "+token, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Login Unauthorized ", HttpStatus.UNAUTHORIZED);
		}
		
	}
	@PutMapping("/changePassword")
	public ResponseEntity<String> adminChangePassword(@RequestBody User userDetails){
		String userNameString = userDetails.getUserName();
		String userPasswordString = userDetails.getPassword();
		
		List<User> fetchedUser=userRepo.findByUserName(userNameString);
		User adminUser = fetchedUser.get(0);
		if(!fetchedUser.isEmpty()&& adminUser.getRole()!="ROLE_ADMIN") {
			adminUser.setPassword(userPasswordString);
			userRepo.save(adminUser);
			return new ResponseEntity<>("Password Successfully changed. New password: "+userPasswordString, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Login Unauthorized ", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		Optional<User> adminUser = userRepo.findByRole("ROLE_ADMIN");
		User user = adminUser.get();
		
		if(adminUser.isPresent()&& user.getToken()!=null) {
			List<User> users = userRepo.findAll();
	        return new ResponseEntity<>(users, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
        
	}
	
	@GetMapping("/getAllCat")
	public  List<Categories> getAllCat() {

		return categoryService.getAllCategories()	;
		
	}
	
	@PostMapping("/addCat")
	public ResponseEntity<Categories> createTutorial(@RequestBody Categories category) {
		try {
			Categories _category = adminCatRepo.save(new Categories(category.getCat_id(), category.getCat_name()));
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delCat/{id}")
	public ResponseEntity<String> delcat(@PathVariable("id") int id) {
		try {
			adminCatRepo.deleteById(id);
			return new ResponseEntity<>("Category with Category id "+id+" deleted",HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/getAllProd")
	public  List<Products> getAllProducts() {
			
		return adminProdRepo.findAll();
		
	}


	
	@PostMapping("/addProd")
	public ResponseEntity<Products> addProd(@RequestBody ProductDTO productDTO) {
		try {
			Products _product = new Products();
			_product.setProd_id(productDTO.getProd_id());
			_product.setProd_name(productDTO.getProd_name());
			_product.setPrice(productDTO.getPrice());
			_product.setCategory(adminCatRepo.findById(productDTO.getCat_id()).get());
			
			adminProdRepo.save(_product);
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delProd/{id}")
	public ResponseEntity<String> delProd(@PathVariable("id") int id) {
		try {
			adminProdRepo.deleteById(id);
			return new ResponseEntity<>("Product with product id "+id+" deleted",HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllTransaction")
	public ResponseEntity<List<Transaction>> getTransactions(){
		Optional<User> adminUser = userRepo.findByRole("ROLE_ADMIN");
		User user = adminUser.get();
		
		if(adminUser.isPresent()&& user.getToken()!=null) {
			List<Transaction> transactions  = transactionRepo.findAll();
	        return new ResponseEntity<>(transactions, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
	}
	@GetMapping("/getTransactionByDate")
	public ResponseEntity<List<Transaction>> getTransactionsByDate(@RequestParam Date startDate, @RequestParam Date endDate){
		Optional<User> adminUser = userRepo.findByRole("ROLE_ADMIN");
		User user = adminUser.get();
		
		if(adminUser.isPresent()&& user.getToken()!=null) {
			List<Transaction> transactions  = transactionRepo.findByListDateRange(startDate,endDate);
	        return new ResponseEntity<>(transactions, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/getTranasctionByCategory")
	public ResponseEntity<List<Transaction>> getTransactionsByCategory(@RequestParam String category){
		Optional<User> adminUser = userRepo.findByRole("ROLE_ADMIN");
		User user = adminUser.get();
		
		if(adminUser.isPresent()&& user.getToken()!=null) {
			List<Transaction> transactions  = transactionRepo.findByCategoryName(category);
	        return new ResponseEntity<>(transactions, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/searchUser")
	public ResponseEntity<List<User>> getUserByUserName(@RequestParam String userName){
		Optional<User> adminUser = userRepo.findByRole("ROLE_ADMIN");
		User user = adminUser.get();
		
		if(adminUser.isPresent()&& user.getToken()!=null) {
			List<User> searchedUser  = userRepo.findByUserName(userName);
	        return new ResponseEntity<>(searchedUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
}

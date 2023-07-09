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
	
	// user controllers
	
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
	public  String getAllCat() {

		return categoryService.getAllCategories().toString();
		
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
	public ResponseEntity<HttpStatus> delcat(@PathVariable("id") int id) {
		try {
			adminCatRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/adminUpdateCat")
	
	@GetMapping("/getAllProd")
	public  String getAllProd() {

		return adminProdRepo.findAll().toString();
		
	}
	
//	make sure to make make a get request before post mapping to get all the available categories in the database
	
	@PostMapping("/addProd")
	public ResponseEntity<Products> addProd(@RequestBody ProductDTO productDTO) {
		try {
			Products _product = new Products();
			_product.setProd_id(productDTO.getProd_id());
			_product.setProd_name(productDTO.getProd_name());
			_product.setPrice(productDTO.getPrice());
			_product.setCategory(adminCatRepo.findById(productDTO.getCat_id()).get());
			
			adminProdRepo.save(_product);
//			Products _product = adminProdRepo.save(new Products(product.getProd_id(),product.getProd_name(),product.getPrice(),product.getCategory()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delProd/{id}")
	public ResponseEntity<HttpStatus> delProd(@PathVariable("id") int id) {
		try {
			adminProdRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
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
			List<User> transactions  = userRepo.findByUserName(userName);
	        return new ResponseEntity<>(transactions, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
}

package com.SportyShoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SportyShoes.repository.UserRepo;
import com.SportyShoes.user.User;

@RestController
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/allUser")
	public String getAllUser() {
		return userRepo.findAll().toString();
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> createTutorial(@RequestBody User userDetails) {
		try {
			User _userDetails = userRepo.save(new User(userDetails.getU_Id(), userDetails.getUserEmail(),userDetails.getUserName()));
			return new ResponseEntity<>(_userDetails, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delUser/{id}")
	public ResponseEntity<HttpStatus> delcat(@PathVariable("id") int id) {
		try {
			userRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

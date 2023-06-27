package com.SportyShoes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SportyShoes.productDetails.Categories;
import com.SportyShoes.productDetails.ProductDTO;
import com.SportyShoes.productDetails.Products;
import com.SportyShoes.repository.CategoryRepo;
import com.SportyShoes.repository.ProductRepo;
import com.SportyShoes.service.CategoryService;


@RestController
public class AdminController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryRepo adminCatRepo;
	
	@Autowired
	ProductRepo adminProdRepo;
	
	@GetMapping("/adminGetAllCat")
	public  String getAllCat() {

		return categoryService.getAllCategories().toString();
		
	}
	
	@PostMapping("/adminAddCat")
	public ResponseEntity<Categories> createTutorial(@RequestBody Categories category) {
		try {
			Categories _category = adminCatRepo.save(new Categories(category.getCat_id(), category.getCat_name()));
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/adminDelCat/{id}")
	public ResponseEntity<HttpStatus> delcat(@PathVariable("id") int id) {
		try {
			adminCatRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/adminUpdateCat")
	
	@GetMapping("/adminGetAllProd")
	public  String getAllProd() {

		return adminProdRepo.findAll().toString();
		
	}
	
//	make sure to make make a get request before post mapping to get all the available categories in the database
	
	@PostMapping("/adminAddProd")
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
	
	@DeleteMapping("/adminDelProd/{id}")
	public ResponseEntity<HttpStatus> delProd(@PathVariable("id") int id) {
		try {
			adminProdRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

package com.SportyShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SportyShoes.entities.Categories;
import com.SportyShoes.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo adminCatRepo;
	
	public List<Categories> getAllCategories() {
		
		List<Categories> categories = adminCatRepo.findAll();
		
		
		return categories;
	}
}

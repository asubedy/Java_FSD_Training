package com.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SportyShoes.entities.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {
	
}

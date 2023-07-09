package com.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SportyShoes.entities.Products;


public interface ProductRepo extends JpaRepository<Products, Integer> {

}

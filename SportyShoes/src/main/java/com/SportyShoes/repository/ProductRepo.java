package com.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SportyShoes.productDetails.Products;


public interface ProductRepo extends JpaRepository<Products, Integer> {

}

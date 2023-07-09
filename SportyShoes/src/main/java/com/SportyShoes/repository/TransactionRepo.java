package com.SportyShoes.repository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SportyShoes.entities.Transaction;


public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	
	@Query(value="SELECT * FROM transaction WHERE date >= ?1 AND date <= ?2 ",nativeQuery = true)
	List<Transaction> findByListDateRange(Date startDate, Date endDate);

	List<Transaction> findByCategoryName(String category);
	
	
}

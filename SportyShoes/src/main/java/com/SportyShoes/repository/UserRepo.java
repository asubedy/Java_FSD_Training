package com.SportyShoes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.SportyShoes.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	List<User> findByUserNameAndPassword(String userName, String password);

	Optional<User> findByToken(String token);

	Optional<User> findByTokenNotNull();

	List<User> findByUserName(String userName);

	Optional<User> findByRole(String string);
}

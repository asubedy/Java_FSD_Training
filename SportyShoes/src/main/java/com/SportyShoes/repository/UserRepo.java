package com.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SportyShoes.user.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

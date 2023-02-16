package com.theatrebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	List<User> findAll();
	
	User findUserById(String id);
	
	User findUserByEmail(String email);
}

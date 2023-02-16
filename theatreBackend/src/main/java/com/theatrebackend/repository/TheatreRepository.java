package com.theatrebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.cinema.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, String> {

	List<Theatre> findAll();
	
	Theatre findHallById(String id);
	
	Theatre findHallByName(String name);
}

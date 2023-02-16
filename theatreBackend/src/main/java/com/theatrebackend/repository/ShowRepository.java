package com.theatrebackend.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {
	
	List<Show> findAll();
	
	Show findShowById(String id);
	
	Show findAllByShowTime(LocalTime time);
	
	Collection<Show> findAllByMovie(Movie movie);
	
	Collection<Show> findAllByShowDate(LocalDate date);


}

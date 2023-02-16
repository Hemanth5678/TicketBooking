package com.theatrebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.cinema.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

	List<Movie> findAll();
	
	Movie findMovieById(String id);
	
	Movie findMovieByTitle(String title);
	
	List<Movie> getMoviesByNowPlaying(Boolean nowPlaying);
	
}

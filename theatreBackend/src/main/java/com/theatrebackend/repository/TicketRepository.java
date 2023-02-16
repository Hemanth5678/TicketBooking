package com.theatrebackend.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Ticket;
import com.theatrebackend.dto.user.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
	
	List<Ticket> findAll();
	
	Ticket findTicketById(String id);
	
	Collection<Ticket> findTicketsByUser(User user);
	
	Collection<Ticket> findTicketsByTheatre(Theatre theatre);

	Collection<Ticket> findTicketsByMovie(Movie movie);

}

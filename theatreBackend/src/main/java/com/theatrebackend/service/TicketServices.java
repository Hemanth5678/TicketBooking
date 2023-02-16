package com.theatrebackend.service;

import java.util.Collection;
import java.util.Optional;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Ticket;
import com.theatrebackend.dto.user.User;


public interface TicketServices {

    Optional<Collection<Ticket>> getAllTickets();

    Optional<Collection<Ticket>> getTicketsByHall(Theatre hall);

    Optional<Collection<Ticket>> getTicketsByUser(User user);

    Optional<Collection<Ticket>> getTicketsByMovie(Movie movie);

    Optional<Ticket> getTicketById(String id);

    Optional<Ticket> addTicket(Ticket ticket, String movieId, String authenticatedUserId);

    Optional<Ticket> updateTicket(Ticket ticket);

    void deleteTicketById(String id);
}

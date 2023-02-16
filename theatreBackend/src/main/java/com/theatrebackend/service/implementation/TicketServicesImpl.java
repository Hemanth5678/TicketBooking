package com.theatrebackend.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Show;
import com.theatrebackend.dto.cinema.Ticket;
import com.theatrebackend.dto.user.User;
import com.theatrebackend.repository.TicketRepository;
import com.theatrebackend.service.TheatreServices;
import com.theatrebackend.service.MovieServices;
import com.theatrebackend.service.ShowServices;
import com.theatrebackend.service.TicketServices;
import com.theatrebackend.service.UserServices;
import com.theatrebackend.utils.ImageProcessing;



@Service
public class TicketServicesImpl implements TicketServices {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowServices showServices;

    @Autowired
    private MovieServices movieServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private TheatreServices hallServices;

    @Override
    public Optional<Collection<Ticket>> getTicketsByUser(User user) {
        return Optional.ofNullable(ticketRepository.findTicketsByUser(user));
    }

    @Override
    public Optional<Collection<Ticket>> getTicketsByMovie(Movie movie) {
        return Optional.ofNullable(ticketRepository.findTicketsByMovie(movie));
    }

    @Override
    public Optional<Collection<Ticket>> getTicketsByHall(Theatre hall) {
        return Optional.ofNullable(ticketRepository.findTicketsByTheatre(hall));
    }

    @Override
    public Optional<Ticket> getTicketById(String id) {
        return Optional.ofNullable(ticketRepository.findTicketById(id));
    }
    
    @Override
    public Optional<Collection<Ticket>> getAllTickets() {
        return Optional.of(ticketRepository.findAll());
    }


    @Override
    public Optional<Ticket> addTicket(Ticket ticket, String movieId, String authenticatedUserId) {
        Optional<Movie> movie = movieServices.getMovieById(movieId);
        assert movie.isPresent();
        ticket.setMovie(movie.get());

        Optional<List<Show>> shows = showServices.getShowsByDateAndTimeByMovie(
                movie.get(), ticket.getDate(), ticket.getTime());
        assert shows.isPresent();
        shows.get().get(0).getSeats().add(ticket.getSeat());
        showServices.updateShow(shows.get().get(0));

        Optional<User> user = userServices.getUserById(authenticatedUserId);
        assert user.isPresent();
        ticket.setUser(user.get());

        Optional<Theatre> theatre = hallServices.getHallByShow(shows.get().get(0));
        assert theatre.isPresent();
        ticket.setTheatre(theatre.get());

        ticket.setImage(ImageProcessing.generateTicketImage(ticket));

        return Optional.of(ticketRepository.save(ticket));
    }

    @Override
    public Optional<Ticket> updateTicket(Ticket ticket) {
        Optional<List<Show>> shows =
                showServices.getShowsByDateAndTimeByMovie(ticket.getMovie(), ticket.getDate(), ticket.getTime());
        assert shows.isPresent();
        shows.get().get(0).getSeats().add(ticket.getSeat());
        showServices.updateShow(shows.get().get(0));

        ticket.setImage(ImageProcessing.generateTicketImage(ticket));

        return Optional.of(ticketRepository.save(ticket));
    }

    @Override
    public void deleteTicketById(String id) {
        Optional<Ticket> ticket = getTicketById(id);
        assert ticket.isPresent();

        Optional<List<Show>> shows = showServices.getShowsByDateAndTimeByMovie(
                        ticket.get().getMovie(), ticket.get().getDate(), ticket.get().getTime());
        assert shows.isPresent();
        shows.get().get(0).getSeats().remove(ticket.get().getSeat());
        showServices.updateShow(shows.get().get(0));

        ticketRepository.deleteById(id);
    }

}
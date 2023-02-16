package com.theatrebackend.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Ticket;
import com.theatrebackend.dto.user.User;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.service.TheatreServices;
import com.theatrebackend.service.MovieServices;
import com.theatrebackend.service.TicketServices;
import com.theatrebackend.service.UserServices;

@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private TheatreServices hallServices;

    @Autowired
    private MovieServices movieServices;

    @Autowired
    private TicketServices ticketServices;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTickets() {
        Optional<Collection<Ticket>> tickets = ticketServices.getAllTickets();

        assert tickets.isPresent();
        if (tickets.get().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Empty tickets list", ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(), "Tickets list", tickets));
    }

    @GetMapping("/auth-id/{id}")
    public ResponseEntity<?> getTicketsByUserId(@PathVariable String id) {
        Optional<Collection<Ticket>> tickets = Optional.empty();
        Optional<User> user = userServices.getUserById(id);
        String email = "";

        if (user.isPresent()) {
            tickets = ticketServices.getTicketsByUser(user.get());
            email = user.get().getEmail();
        }

        assert tickets.isPresent();
        if (tickets.get().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Empty tickets list for email: " + email, ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Tickets list for email: " + email, tickets));
    }

    @GetMapping("/movie-title/{movieTitle}")
    public ResponseEntity<?> getTicketsByMovieTitle(@PathVariable String movieTitle) {
        Optional<Collection<Ticket>> tickets = Optional.empty();
        Optional<Movie> movie = movieServices.getMovieByTitle(movieTitle);

        if (movie.isPresent())
            tickets = ticketServices.getTicketsByMovie(movie.get());

        assert tickets.isPresent();
        if (tickets.get().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Empty tickets list for movie: " + movieTitle, ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Tickets list for movie: " + movieTitle, tickets));
    }

    @GetMapping("/hall-name/{hallName}")
    public ResponseEntity<?> getTicketByHall(@PathVariable String hallName) {
        Optional<Collection<Ticket>> tickets = Optional.empty();
        Optional<Theatre> hall = hallServices.getHallByName(hallName);

        if (hall.isPresent())
            tickets = ticketServices.getTicketsByHall(hall.get());

        assert tickets.isPresent();
        if (tickets.get().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Empty tickets list for hall: " + hallName, ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Tickets list for hall: " + hallName, tickets));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable String id) {
        Optional<Ticket> ticket = ticketServices.getTicketById(id);

        if (ticket.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Ticket not found for id: " + id, ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Ticket found for id: " + id, ticket));
    }

    @PostMapping("/{movieId}/{authenticatedUserId}")
    public ResponseEntity<?> addTicket(@RequestBody Ticket ticket,
                                       @PathVariable String movieId, @PathVariable String authenticatedUserId) {
        Optional<Movie> existedMovie = movieServices.getMovieById(movieId);
        if (existedMovie.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Movie not found for id: " + movieId, ""));

        Optional<User> existedUser = userServices.getUserById(authenticatedUserId);
        if (existedUser.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "User not found for id: " + authenticatedUserId, ""));

        ticket.setUser(existedUser.get());
        ticket.setMovie(existedMovie.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Ticket Added successfully",
                ticketServices.addTicket(ticket, movieId, authenticatedUserId)));
    }

    @PutMapping()
    @PreAuthorize("#ticket.getUser().email == authentication.name")
    public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket) {
        Optional<Ticket> existedTicket = ticketServices.getTicketById(ticket.getId());

        if (existedTicket.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Ticket not found", ticket));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Ticket updated successfully", ticketServices.updateTicket(ticket)));

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        Optional<Ticket> existedTicket = ticketServices.getTicketById(id);

        if (existedTicket.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Ticket not found for id: ", id));

        ticketServices.deleteTicketById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Ticket deleted successfully", ""));
    }
}
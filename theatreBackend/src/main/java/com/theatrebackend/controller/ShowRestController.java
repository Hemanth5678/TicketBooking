package com.theatrebackend.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.dto.cinema.Show;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.service.MovieServices;
import com.theatrebackend.service.ShowServices;


@RestController
@RequestMapping("/api/show")
public class ShowRestController {

    @Autowired
    private ShowServices showServices;

    @Autowired
    private MovieServices movieServices;
    
    @GetMapping
    public ResponseEntity<?> getAllShows() {
    	Optional<Collection<Show>> shows= showServices.getAllShows();
    	 return ResponseEntity.status(HttpStatus.OK).body(shows);
    }

    @GetMapping("/movie-id/{movieId}")
    public ResponseEntity<?> getShowsByMovieId(@PathVariable String movieId) {
        Optional<Movie> movie = movieServices.getMovieById(movieId);

        assert movie.isPresent();
        Optional<Collection<Show>> shows = showServices.getAllShowsByMovie(movie.get());

        assert shows.isPresent();
        if (shows.get().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Empty shows list for movie id: " + movieId, ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Shows list for movie id: " + movieId, shows));
    }
}

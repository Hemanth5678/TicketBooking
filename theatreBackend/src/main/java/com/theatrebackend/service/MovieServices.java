package com.theatrebackend.service;

import java.util.Collection;
import java.util.Optional;

import com.theatrebackend.dto.cinema.Movie;


public interface MovieServices {

    Optional<Collection<Movie>> getAllMovies();

    Optional<Collection<Movie>> getMoviesNowPlaying();

    Optional<Movie> getMovieById(String id);

    Optional<Movie> getMovieByTitle(String title);

    Optional<Movie> addMovie(Movie movie);

    Optional<Movie> updateMovie(Movie movie);

    void deleteMovieById(String id);
}

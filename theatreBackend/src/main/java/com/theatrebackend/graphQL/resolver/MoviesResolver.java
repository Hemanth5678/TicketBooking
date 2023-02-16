package com.theatrebackend.graphQL.resolver;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.cinema.Movie;
import com.theatrebackend.repository.MovieRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class MoviesResolver implements GraphQLQueryResolver, GraphQLMutationResolver{

	@Autowired
	private MovieRepository movieRepository;
	
    public Collection<Movie> findAllMovies() {
        return movieRepository.findAll();
    }
    
    public Collection<Movie> findNowPlayingMovies() {
        return movieRepository.getMoviesByNowPlaying(true);
    }
    
    public Movie newMovie(Movie movie) {

        movieRepository.save(movie);

        return movie;
    }
	
}

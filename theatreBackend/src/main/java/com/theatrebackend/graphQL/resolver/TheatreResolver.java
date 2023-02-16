package com.theatrebackend.graphQL.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.repository.TheatreRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class TheatreResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	public List<Theatre> findAllTheatres() {
		
		return theatreRepository.findAll();
		
	}

}

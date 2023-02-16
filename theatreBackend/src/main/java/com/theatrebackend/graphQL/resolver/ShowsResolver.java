package com.theatrebackend.graphQL.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.cinema.Show;
import com.theatrebackend.repository.ShowRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ShowsResolver implements GraphQLMutationResolver, GraphQLQueryResolver {

	@Autowired
	private ShowRepository showRepository;
	
	public List<Show> findAllShows() {
		return showRepository.findAll();
	}
}

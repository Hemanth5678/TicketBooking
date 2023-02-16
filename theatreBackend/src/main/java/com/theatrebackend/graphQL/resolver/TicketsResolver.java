package com.theatrebackend.graphQL.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.cinema.Ticket;
import com.theatrebackend.repository.TicketRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class TicketsResolver implements GraphQLMutationResolver, GraphQLQueryResolver {

	@Autowired
	private TicketRepository ticketRepository;
	
	public List<Ticket> findAllTickets() {
		return ticketRepository.findAll();
	}
}

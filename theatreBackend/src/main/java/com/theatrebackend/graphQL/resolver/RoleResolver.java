package com.theatrebackend.graphQL.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.user.Role;
import com.theatrebackend.repository.RoleRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class RoleResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

	@Autowired
	private RoleRepository roleRepository;
    
    public Iterable<Role> findAllRoles() {
    	return roleRepository.findAll();
    }
}

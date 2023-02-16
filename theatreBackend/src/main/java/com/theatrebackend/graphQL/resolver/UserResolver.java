package com.theatrebackend.graphQL.resolver;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.user.User;
import com.theatrebackend.payload.request.JWTRequest;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.payload.response.JWTResponse;
import com.theatrebackend.repository.UserRepository;
import com.theatrebackend.security.jwt.JWTUtil;
import com.theatrebackend.service.UserServices;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserServices userServices;
	
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private JWTUtil jwtUtil;
	
    public Collection<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User newUser(User user) {
        userRepository.save(user);
        return user;
    }
    
    public JWTResponse userLogin(String email, String password) {
    	JWTRequest body = new JWTRequest(email, password);
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authenticationManager.authenticate(authInputToken);
            String jwtToken = jwtUtil.generateToken(body.getEmail());

            Optional<User> loggedUser = userServices.getUserByEmail(body.getEmail());

            return new JWTResponse(jwtToken, loggedUser.get());
    }
    
    public User userByEmail(String email) {
    	return userRepository.findUserByEmail(email);
    }
    
}

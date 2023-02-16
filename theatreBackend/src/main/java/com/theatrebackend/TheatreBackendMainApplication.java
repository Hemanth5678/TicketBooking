package com.theatrebackend;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.theatrebackend.graphQL.error.GraphQLErrorAdapter;
import com.theatrebackend.repository.UserRepository;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;

/**
 * @author Hemanthav
 *
 */
@SpringBootApplication
@EnableCaching
public class TheatreBackendMainApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext= SpringApplication.run(TheatreBackendMainApplication.class, args);
		//applicationContext.close();
	}
	
	
//	@Bean
//	public Query query(UserRepository userRepository) {
//		return new Query(userRepository);
//	}
//
//	@Bean
//	public Mutation mutation(UserRepository userRepository) {
//		return new Mutation(userRepository);
//	}
}

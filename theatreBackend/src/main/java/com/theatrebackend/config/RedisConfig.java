package com.theatrebackend.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Hemanthav
 *
 */
@Configuration
public class RedisConfig {

	  @Autowired
	  ObjectMapper mapper;

	  @Autowired
	  RedisConnectionFactory connectionFactory;

	  @Bean
	  RedisTemplate<String, Object> redisTemplate() {
	    final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	    Jackson2JsonRedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
	    valueSerializer.setObjectMapper(mapper);
	    redisTemplate.setConnectionFactory(connectionFactory);
	    redisTemplate.setKeySerializer(new StringRedisSerializer());
	    redisTemplate.setValueSerializer(valueSerializer);
	    return redisTemplate;
	  }
}

package com.theatrebackend.dto.review;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Message {

	@Id
	private String id;
	
	private String email;
	
	private String subject;
	
	private String message;
}

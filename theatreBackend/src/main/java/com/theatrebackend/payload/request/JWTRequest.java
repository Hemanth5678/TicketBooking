package com.theatrebackend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTRequest {
	
	private String email;
	
	private String password;

}

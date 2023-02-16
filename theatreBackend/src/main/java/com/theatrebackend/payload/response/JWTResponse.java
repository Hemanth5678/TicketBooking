package com.theatrebackend.payload.response;



import com.theatrebackend.dto.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {

	private String token;
	
	private User user;
}

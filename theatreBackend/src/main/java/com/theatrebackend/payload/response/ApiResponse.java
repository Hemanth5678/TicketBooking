package com.theatrebackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private Integer status;
	
	private String timeStamp;
	
	private String message;
	
	private Object body; 
}

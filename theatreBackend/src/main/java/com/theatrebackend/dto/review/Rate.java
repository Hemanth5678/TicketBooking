package com.theatrebackend.dto.review;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Rate {

	@Id
	private String id;
	
	private Boolean firstTime;
	
	private Integer rate;
	
	private String review;
}

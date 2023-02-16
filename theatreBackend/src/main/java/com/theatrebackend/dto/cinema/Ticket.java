package com.theatrebackend.dto.cinema;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.theatrebackend.dto.user.User;

import lombok.Data;

@Data
@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Movie  movie;
	
	@ManyToOne
	private Theatre theatre;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private Integer seat;
	
	private byte[] image;
	
}

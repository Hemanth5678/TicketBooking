package com.theatrebackend.dto.cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Shows_table")
public class Show {

	@Id
	private String id;
	
	@ManyToOne
	private Movie movie;
	
	private LocalDate showDate;
	
	private LocalTime showTime;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Integer> seats = new HashSet<>();
}

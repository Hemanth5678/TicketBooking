package com.theatrebackend.dto.cinema;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	private Float rate;
	
	private Boolean nowPlaying;
	
	private Integer duration;
	
	private Integer year;
	
	private String description;
	
	private String title;
	
	private String language;
	
	private String imgURL;
	
	private String ytTrailerId;
	
	private String director;
	
	@ElementCollection
	private Collection<String> actors, writers;
}

package com.theatrebackend.dto.cinema;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name")})
public class Theatre {

	@Id
	private String id;
	
	private String name;
	
	private Integer capacity;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Show> shows;
}

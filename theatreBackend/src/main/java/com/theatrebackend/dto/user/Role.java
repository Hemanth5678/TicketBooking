package com.theatrebackend.dto.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.theatrebackend.dto.enums.EROLE;

import lombok.Data;


@Data
@Entity
public class Role {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String roleId;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EROLE roleName;

}

package com.theatrebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.enums.EROLE;
import com.theatrebackend.dto.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role findRoleByRoleName(EROLE roleName);
	
}

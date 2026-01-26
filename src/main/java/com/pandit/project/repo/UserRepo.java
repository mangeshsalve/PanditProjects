package com.pandit.project.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandit.project.model.Users;

public interface UserRepo extends JpaRepository<Users, UUID>{
	
	boolean existsByEmail(String email);

}

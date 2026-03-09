package com.pandit.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.config.JwtAuthService;
import com.pandit.project.dto.LoginDto;
import com.pandit.project.dto.LoginResponseDto;
import com.pandit.project.model.Users;
import com.pandit.project.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final UserRepo repo;
	private final JwtAuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto logindto){
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    logindto.getUsername(),
	                    logindto.getPassword()
	            )
	    );
	    

	    // Example: fetch user from DB to get userId + role
	    Users user = repo.findByUsername(logindto.getUsername())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    String token = authService.generateToken(
	            user.getUsername(),
	            user.getUserId(),
	            user.getRole().name()
	    );

	    return ResponseEntity.ok(
	            new LoginResponseDto(token, user.getUsername(), user.getRole().name(),user.getUserId(),user.getName())
	    );
	}

}

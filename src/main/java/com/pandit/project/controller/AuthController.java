package com.pandit.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.config.JwtAuthService;
import com.pandit.project.dto.LoginDto;
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
	public ResponseEntity<String> loginUser(@RequestBody LoginDto logindto){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		return new ResponseEntity<>("Login successfull",HttpStatus.ACCEPTED);
		
	}

}

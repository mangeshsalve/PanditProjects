package com.pandit.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.dto.UserRequestDtos;
import com.pandit.project.dto.UserResponse;
import com.pandit.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
	
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponse> addUsers(@RequestBody UserRequestDtos userRequsetDtos){
		userService.addUsers(userRequsetDtos);
		return null;
	}

}

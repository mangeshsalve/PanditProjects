package com.pandit.project.service;

import org.springframework.stereotype.Component;

import com.pandit.project.dto.UserRequestDtos;
import com.pandit.project.dto.UserResponse;
import com.pandit.project.exception.UserExceptions;
import com.pandit.project.model.Users;
import com.pandit.project.repo.UserRepo;
import com.pandit.project.util.UserTransformer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepo repo;
	private final UserTransformer userTransformer;

	public UserResponse addUsers(UserRequestDtos userRequsetDtos){
		if(checkDuplicateEmail(userRequsetDtos.getEmail())) {
			throw new UserExceptions("Email is already exists");
		}
		Users save = repo.save(userTransformer.toUserEntity(userRequsetDtos));
		return userTransformer.toUserResponse(save);
	}

	private boolean checkDuplicateEmail(String email) {
		// TODO Auto-generated method stub
		return repo.existsByEmail(email);
	}

}

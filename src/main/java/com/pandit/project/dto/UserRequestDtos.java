package com.pandit.project.dto;

import java.util.UUID;

import com.pandit.project.model.Role;

import lombok.Data;
@Data
public class UserRequestDtos {
	
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String username;
	private UserAddressDto userAddressDto;
	private String mobileNumber;
	private Role role;
}

package com.pandit.project.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.pandit.project.model.Role;
import com.pandit.project.model.UserAddress;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

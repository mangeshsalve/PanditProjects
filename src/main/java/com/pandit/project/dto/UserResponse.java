package com.pandit.project.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.pandit.project.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
	
	private UUID id;
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String username;
	private UserAddressDto userAddressDto;
	private String mobileNumber;
	private Role role;
	private OffsetDateTime createdDateTime;
	private OffsetDateTime updatedDateTime;

}

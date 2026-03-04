package com.pandit.project.util;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import com.pandit.project.dto.UserAddressDto;
import com.pandit.project.dto.UserRequestDtos;
import com.pandit.project.dto.UserResponse;
import com.pandit.project.model.Role;
import com.pandit.project.model.UserAddress;
import com.pandit.project.model.UserIdData;
import com.pandit.project.model.Users;
import com.pandit.project.repo.UserIdDataRepo;
@Component
public class UserTransformer {

	@Autowired
	private  PasswordEncoder encoder;
	@Autowired
	private  UserIdDataRepo dataRepo;
	private static final TimeBasedEpochGenerator generator=Generators.timeBasedEpochGenerator();
	public  Users toUserEntity(UserRequestDtos requestDtos) {
		return Users.builder()
				.id(generator.generate())
				.name(requestDtos.getName())
				.email(requestDtos.getEmail())
				.mobileNumber(requestDtos.getMobileNumber())
				.role(Role.USER)
				.username(requestDtos.getUsername())
				.userAddress(requestDtos.getUserAddressDto()!=null?buildUserAddressDto(requestDtos.getUserAddressDto()):null)
				.password(encoder.encode( requestDtos.getPassword()))
				.userId(data())
				.createdDateTime(OffsetDateTime.now())
				.build();
	}

	private   UserAddress buildUserAddressDto(UserAddressDto userAddressDto) {
		// TODO Auto-generated method stub
		return UserAddress.builder()
				.id(generator.generate())
				.city(userAddressDto.getCity())
				.country(userAddressDto.getCountry())
				.state(userAddressDto.getState())
				.pincode(userAddressDto.getPincode())
				.streetName(userAddressDto.getStreetName())
				.build();
	}

	public  UserResponse toUserResponse(Users save) {
		// TODO Auto-generated method stub
		return UserResponse.builder()
				.id(save.getId())
				.name(save.getName())
				.userId(save.getUserId())
				.email(save.getEmail())
				.username(save.getUsername())
				.mobileNumber(save.getMobileNumber())
				.role(save.getRole())
				.createdDateTime(save.getCreatedDateTime())
				.userAddressDto(save.getUserAddress()!=null? buildUserAddress(save.getUserAddress()):null)
				.build();
	}

	private  UserAddressDto buildUserAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		return UserAddressDto.builder()
				.city(userAddress.getCity())
				.country(userAddress.getCountry())
				.pincode(userAddress.getPincode())
				.state(userAddress.getState())
				.streetName(userAddress.getStreetName())
				.build();
	}
	
	public Integer data() {
		UserIdData byId = dataRepo.findById("USER").get();
		Integer userId=byId.getUserId()+1;
		byId.setUserId(userId);
		dataRepo.save(byId);
		return userId;
	}


}

package com.pandit.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAddressDto {
	
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String pincode;

}

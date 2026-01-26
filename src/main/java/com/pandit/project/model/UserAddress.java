package com.pandit.project.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
	@Id
	private UUID id;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String pincode;
	

}

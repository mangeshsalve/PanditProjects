package com.pandit.project.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String username;
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "address")
	private UserAddress userAddress;
	private String mobileNumber;
	private Role role;
	@Column(name="userId",unique = true)
	private Integer userId;
	private OffsetDateTime createdDateTime;
	private OffsetDateTime updatedDateTime;
}

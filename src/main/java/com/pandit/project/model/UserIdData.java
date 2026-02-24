package com.pandit.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_id_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserIdData {
	@Id
	private String userType;
	private Integer userId;

}

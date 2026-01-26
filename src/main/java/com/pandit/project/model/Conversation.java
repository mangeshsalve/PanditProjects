package com.pandit.project.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Conversation {
	@Id
	private UUID id;	
	private UUID userId;	
	private UUID panditId;	
	private OffsetDateTime createdAt;	
}

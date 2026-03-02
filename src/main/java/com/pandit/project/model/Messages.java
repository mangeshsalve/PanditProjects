package com.pandit.project.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Messages {
	
	@Id
	private UUID id;
	private String content;
	private OffsetDateTime createdAt;
	private Integer senderId;
	@ManyToOne
	private Conversation conversation;

}

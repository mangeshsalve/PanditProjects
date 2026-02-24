package com.pandit.project.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pandit.project.model.Messages;

public interface MessageRepo extends JpaRepository<Messages, UUID> {

	Page<Messages> findByConversationIdOrderByCreatedAtDesc(UUID id, PageRequest pageRequest);

	Optional<Messages> findTopByConversationIdOrderByCreatedAtDesc(UUID id);

	
}

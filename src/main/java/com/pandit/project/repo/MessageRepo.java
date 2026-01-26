package com.pandit.project.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandit.project.model.Messages;

public interface MessageRepo extends JpaRepository<Messages, UUID> {

}

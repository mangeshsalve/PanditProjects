package com.pandit.project.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pandit.project.model.Conversation;
@Repository
public interface ConversationsRepo extends JpaRepository<Conversation, UUID>{

	Optional<Conversation> findByUserIdAndPanditId(Integer userId, Integer panditId);

	List<Conversation> findByPanditIdOrderByCreatedAtDesc(Integer userId);

	List<Conversation> findByUserIdOrderByCreatedAtDesc(Integer userId);

}

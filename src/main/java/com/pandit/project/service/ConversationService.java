package com.pandit.project.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pandit.project.dto.PanditConversationResponseDto;
import com.pandit.project.dto.UserConversationResponseDto;
import com.pandit.project.model.Conversation;
import com.pandit.project.model.Messages;
import com.pandit.project.model.Users;
import com.pandit.project.repo.ConversationsRepo;
import com.pandit.project.repo.MessageRepo;
import com.pandit.project.repo.UserRepo;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class ConversationService {

	private final ConversationsRepo conversationsRepo;
	private final UserRepo userRepo;
	private final MessageRepo messageRepo;

	public Conversation createConversation(Integer userId, Integer panditId) {
		// TODO Auto-generated method stub
	Conversation conversation=	conversationsRepo.findByUserIdAndPanditId(userId,panditId).orElseGet(()->{
			return conversationsRepo.save(Conversation.builder()
					.id(UUID.randomUUID())
					.panditId(panditId)
					.userId(userId)
					.createdAt(OffsetDateTime.now())
					.build());
		});
	
	return conversation;
		
	}

	public List<PanditConversationResponseDto> getAllPanditConversations(Integer userId) {
		// TODO Auto-generated method stub
		List<Conversation> list = conversationsRepo.findByPanditIdOrderByCreatedAtDesc(userId);
	return	list.stream().map(c->{
			Users users = userRepo.findByUserId(c.getUserId()).orElseThrow();
            Messages lastMsg = messageRepo.findTopByConversationIdOrderByCreatedAtDesc(c.getId())
                    .orElse(null);
           return PanditConversationResponseDto.builder()
            .conversationId(c.getId())
            .userId(c.getUserId())
            .userName(users.getName())
            .userUsername(users.getUsername())
            .lastMessage(lastMsg!=null?lastMsg.getContent():null)
            .lastMessageAt(lastMsg!=null?lastMsg.getCreatedAt():null)
            .build();

		}).toList();
	}
	
	public List<UserConversationResponseDto> getAllUserConversations(Integer userId) {
		// TODO Auto-generated method stub
		List<Conversation> list = conversationsRepo.findByUserIdOrderByCreatedAtDesc(userId);
	return	list.stream().map(c->{
			Users users = userRepo.findByUserId(c.getPanditId()).orElseThrow();
            Messages lastMsg = messageRepo.findTopByConversationIdOrderByCreatedAtDesc(c.getId())
                    .orElse(null);
           return UserConversationResponseDto.builder()
            .conversationId(c.getId())
            .panditId(c.getPanditId())
            .userName(users.getName())
            .userUsername(users.getUsername())
            .lastMessage(lastMsg!=null?lastMsg.getContent():null)
            .lastMessageAt(lastMsg!=null?lastMsg.getCreatedAt():null)
            .build();

		}).toList();
	}

}

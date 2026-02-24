package com.pandit.project.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.pandit.project.controller.MessageResponse;
import com.pandit.project.dto.ChatMessageDto;
import com.pandit.project.dto.MessageRequest;
import com.pandit.project.exception.ConversationException;
import com.pandit.project.exception.UserExceptions;
import com.pandit.project.model.Conversation;
import com.pandit.project.model.Messages;
import com.pandit.project.repo.ConversationsRepo;
import com.pandit.project.repo.MessageRepo;
import com.pandit.project.util.MessageTransformer;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class MessageService {

	private final MessageRepo messageRepo;
	private final MessageTransformer transformer;
	private final ConversationsRepo conversationsRepo;

    private final SimpMessagingTemplate messagingTemplate;
	
	public MessageResponse sendMessage(MessageRequest request, Integer userId) {
		Conversation conversation = conversationsRepo.findById(request.getConversationId()).orElseThrow(()->  new ConversationException("Conversation not found"));
		if(conversation.getUserId().equals(userId)|| conversation.getPanditId().equals(userId)) {
			messageRepo.save(transformer.toMessageEntity(request,userId));
			return MessageResponse.builder()
					.status("Success")
					.build();
		}else {
			throw new UserExceptions("User is not a part of this conversation");
		}
	}
	

	public Page<Messages> getMessage(UUID conversationId, Integer userId, PageRequest pageRequest) {
		// TODO Auto-generated method stub

		Conversation conversation = conversationsRepo.findById(conversationId).orElseThrow(()->  new ConversationException("Conversation not found"));
	
		Page<Messages> page = messageRepo.findByConversationIdOrderByCreatedAtDesc(conversation.getId(),pageRequest);
		return page;
	}


	public boolean saveMessage(ChatMessageDto dto) {
		// TODO Auto-generated method stub
		Conversation conversation = conversationsRepo.findById(dto.getConversationId()).orElseThrow(()->  new ConversationException("Conversation not found"));
		if(conversation.getUserId().equals(dto.getSenderId())|| conversation.getPanditId().equals(dto.getSenderId())) {

			messageRepo.save(transformer.toMessageEntity(			MessageRequest.builder()
					.content(dto.getMessage())
					.conversationId(dto.getConversationId())
					.build(),dto.getSenderId()));
			return true;

		}else {
			return false;
		}
	}


	public void broadcast(ChatMessageDto saved) {
		// TODO Auto-generated method stub
	     messagingTemplate.convertAndSend(
	             "/topic/conversation/" + saved.getConversationId(),
	             saved  );
	}

}

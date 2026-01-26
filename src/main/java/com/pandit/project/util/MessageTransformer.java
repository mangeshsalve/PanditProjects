package com.pandit.project.util;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import com.pandit.project.dto.MessageRequest;
import com.pandit.project.exception.ConversationException;
import com.pandit.project.model.Conversation;
import com.pandit.project.model.Messages;
import com.pandit.project.repo.ConversationsRepo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class MessageTransformer {

	private final ConversationsRepo conversationsRepo;
	private final TimeBasedEpochGenerator generator=Generators.timeBasedEpochGenerator();
	
	public Messages toMessageEntity(MessageRequest request, Integer userId) {
		// TODO Auto-generated method stub
		return Messages.builder()
				.id(generator.generate())
				.content(request.getContent())
				.conversation(getConversation(request.getConversationId()))
				.createdAt(OffsetDateTime.now())
				.senderId(userId)
				.build();
	}

	private Conversation getConversation(UUID conversationId) {
		// TODO Auto-generated method stub
		Conversation conversation = conversationsRepo.findById(conversationId).orElseThrow(()-> new ConversationException("Coonversation Not found"));
		return conversation;
	}

	
}

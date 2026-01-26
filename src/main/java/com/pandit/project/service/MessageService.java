package com.pandit.project.service;


import org.springframework.stereotype.Component;

import com.pandit.project.controller.MessageResponse;
import com.pandit.project.dto.MessageRequest;
import com.pandit.project.repo.MessageRepo;
import com.pandit.project.util.MessageTransformer;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class MessageService {

	private final MessageRepo messageRepo;
	private final MessageTransformer transformer;
	
	public MessageResponse sendMessage(MessageRequest request, Integer userId) {
		messageRepo.save(transformer.toMessageEntity(request,userId));
		return MessageResponse.builder()
				.status("Success")
				.build();
	}

}

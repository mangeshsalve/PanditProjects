package com.pandit.project.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.pandit.project.dto.ChatMessageDto;
import com.pandit.project.service.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
	
	private final MessageService messageService;
	
		@MessageMapping("/chat")
	    public void sendMessage(ChatMessageDto dto) {

	        // 1️⃣ Save in DB
	       boolean saveMessage = messageService.saveMessage(dto);

	        // 2️⃣ Broadcast to specific conversation
	       if(saveMessage)
	        messageService.broadcast(dto);
	    }

}

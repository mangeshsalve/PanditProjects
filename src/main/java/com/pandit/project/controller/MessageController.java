package com.pandit.project.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.dto.MessageRequest;
import com.pandit.project.model.Messages;
import com.pandit.project.service.MessageService;
import com.pandit.project.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService messageService;
	
	@PostMapping
	public MessageResponse sendMessage(@RequestBody MessageRequest request) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	Integer userId=TokenUtil.getUserId(authentication);
		return messageService.sendMessage(request,userId);
	}

	@GetMapping("/conversations/{conversationId}/messages")
	public Page<Messages> getMessage(@PathVariable("conversationId")UUID conversationId,@RequestParam("page")int page,@RequestParam("size")int size) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId=TokenUtil.getUserId(authentication);
		PageRequest pageRequest = PageRequest.of(page, size);
	return 	messageService.getMessage(conversationId,userId,pageRequest);
		
		
	}


}

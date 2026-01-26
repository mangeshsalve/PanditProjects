package com.pandit.project.controller;

import com.pandit.project.util.TokenUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.dto.MessageRequest;
import com.pandit.project.service.MessageService;

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



}

package com.pandit.project.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.dto.PanditConversationResponseDto;
import com.pandit.project.dto.UserConversationResponseDto;
import com.pandit.project.model.Conversation;
import com.pandit.project.service.ConversationService;
import com.pandit.project.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/conversation")
@RequiredArgsConstructor
public class ConversationController {
	
	private final ConversationService service;
	
	@PostMapping
	public Conversation createConversations(@RequestBody Integer panditId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId=TokenUtil.getUserId(authentication);
	return	service.createConversation(userId,panditId);
		
	}
	
	@GetMapping
	public List<PanditConversationResponseDto> getAllPanditConversations(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId=TokenUtil.getUserId(authentication);
		return service.getAllPanditConversations(userId);
	}
	
	@GetMapping("/users")
	public List<UserConversationResponseDto> getAllUserConversations(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer userId=TokenUtil.getUserId(authentication);
		return service.getAllUserConversations(userId);
	}

}

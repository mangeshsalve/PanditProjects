package com.pandit.project.controller;


import java.net.http.HttpRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandit.project.service.OpenAIService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	private final OpenAIService openAiService;
	
	public ChatController(OpenAIService openAiService) {
		this.openAiService=openAiService;
	}
	
	@GetMapping("/test")
	public String test() {
		return "Testing....";
	}
	//,consumes = MediaType.TEXT_PLAIN_VALUE,produces = MediaType.TEXT_PLAIN_VALUE
	@PostMapping("/ask")
	public String getChatResponse(@RequestBody String message) {
		return openAiService.getMessage(message);
	}
	
}

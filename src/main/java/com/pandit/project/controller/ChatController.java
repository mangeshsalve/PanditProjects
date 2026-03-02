package com.pandit.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@GetMapping("/test")
	public String test() {
		return "Testing....";
	}
	//,consumes = MediaType.TEXT_PLAIN_VALUE,produces = MediaType.TEXT_PLAIN_VALUE
	@PostMapping("/ask")
	public String getChatResponse(@RequestBody String message) {
		return "";
	}
	
}

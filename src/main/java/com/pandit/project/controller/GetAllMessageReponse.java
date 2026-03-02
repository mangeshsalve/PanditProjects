package com.pandit.project.controller;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllMessageReponse {

	private UUID conversationId;
	private List<MessageResponseDto> content;
}

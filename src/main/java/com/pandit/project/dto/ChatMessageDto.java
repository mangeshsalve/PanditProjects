package com.pandit.project.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatMessageDto {
    private UUID conversationId;
    private Integer senderId;
    private String content;
    private OffsetDateTime createdAt;
}

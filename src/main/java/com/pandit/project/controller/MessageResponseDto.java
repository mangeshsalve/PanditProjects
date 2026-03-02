package com.pandit.project.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageResponseDto {

    private UUID messageId;
    private UUID conversationId;

    private Integer senderId;   // userId (integer)
    private String senderRole;  // USER / PANDIT (optional)

    private String message;
    private LocalDateTime createdAt;

    // getters/setters
}
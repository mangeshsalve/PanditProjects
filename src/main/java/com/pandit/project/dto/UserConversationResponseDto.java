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
public class UserConversationResponseDto {
    private UUID conversationId;

    private Integer panditId;
    private String userName;
    private String userUsername;

    private String lastMessage;
    private OffsetDateTime lastMessageAt;
}

package com.pandit.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker
        // Messages with destinations starting with /topic or /queue go to the broker
        registry.enableSimpleBroker("/topic", "/queue");

        // Messages with /app prefix are routed to @MessageMapping methods
        registry.setApplicationDestinationPrefixes("/app");

        // User-specific messages use /user prefix
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the /ws endpoint for WebSocket connections
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")  // Configure CORS for your needs
                .withSockJS();  // Enable SockJS fallback for browsers without WebSocket
    }
}
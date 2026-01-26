package com.pandit.project.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class OpenAIService {
	
	private final WebClient client;
	
	public OpenAIService(WebClient client) {
	       this.client = client;
	}

	public String getMessage(String message) {
		// TODO Auto-generated method stub
        String requestBody = """
        {
          "model": "gpt-5.1",
          "messages": [
            {
              "role": "system",
              "content": "You are a knowledgeable Hindu pandit. Explain pujas, rituals, and spiritual concepts in simple, respectful language. Do not invent scriptures or claim supernatural authority."
            },
            {
              "role": "user",
              "content": "%s"
            }
          ],
          "temperature": 0.6
        }
        """.formatted(message.replace("\"", "\\\""));

        return client.post()
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .map(response -> {
                var choices = (List<Map<String, Object>>) response.get("choices");
                var message1 = (Map<String, Object>) choices.get(0).get("message");
                return message1.get("content").toString();
            })
            .block();
	}

}

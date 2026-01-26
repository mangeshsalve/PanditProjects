package com.pandit.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class SecurityConfig {
	@Value(value = "${OPENAI_API_KEY}")
	private String openAPiKey;
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(
				auth-> auth.anyRequest().permitAll()
			
				);
		return 		http.build();
	}

	 @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	
	
	@Bean
	public WebClient getWebClient() {
		return WebClient.builder()
	               .baseUrl("https://api.openai.com/v1/chat/completions")
	               .defaultHeader(HttpHeaders.AUTHORIZATION,
	                   "Bearer " + openAPiKey)
	               .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	               .build();
	}
}

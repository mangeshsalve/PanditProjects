package com.pandit.project.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	
	@Bean
	public  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http
		.authorizeHttpRequests(
				auth-> 
					auth.requestMatchers("/api/auth/login").permitAll()
					 .requestMatchers("/ws/**").permitAll()
					.requestMatchers("/api/users/registration").permitAll()
					   .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.anyRequest().authenticated()
				
				)  .addFilterBefore(jwtAuthFilter,
		                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)

		.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		
				http.csrf(AbstractHttpConfigurer::disable);
		return 		http.build();
	}

	@Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowedOrigins(List.of(   
				"http://127.0.0.1:5173",
                "http://localhost:5173",
                "https://pandit-front-end.vercel.app",
                "https://panditprojects.onrender.com"
				));
		// TODO Auto-generated method stub
		config.setAllowedHeaders(List.of("Authorization","Content-Type"));
		 config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	 @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedOrigins("https://pandit-front-end.vercel.app")
	                        .allowedMethods("*")
	                        .allowedHeaders("*");
	            }
	        };
	    }
}

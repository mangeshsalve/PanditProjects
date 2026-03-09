package com.pandit.project.dto;

public class LoginResponseDto {
	 private String token;
	    private String username;
	    private String role;
	    private Integer userId;
	    private String name;

	    public LoginResponseDto(String token, String username, String role,Integer userId,String name) {
	        this.token = token;
	        this.username = username;
	        this.role = role;
	        this.userId=userId;
	        this.name=name;
	    }

	    public String getToken() { return token; }
	    public String getUsername() { return username; }
	    public String getRole() { return role; }
	    public Integer getUserId() {return userId;}
	    public String getName() {
	    	return name;
	    }
}

package com.pandit.project.util;

import org.springframework.security.core.Authentication;

public class TokenUtil {

	public static Integer getUserId(Authentication authentication) {
		return getDetails(authentication);
	}

	private static Integer getDetails(Authentication authentication) {
		// TODO Auto-generated method stub
		Integer userId=(Integer)authentication.getPrincipal();
		return userId;
	}
}

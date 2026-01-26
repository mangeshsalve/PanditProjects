package com.pandit.project.util;

import org.springframework.security.core.Authentication;

public class TokenUtil {

	public static Integer getUserId(Authentication authentication) {
		return Integer.parseInt(getDetails(authentication));
	}

	private static String getDetails(Authentication authentication) {
		// TODO Auto-generated method stub
		String userId=(String)authentication.getPrincipal();
		return userId;
	}
}

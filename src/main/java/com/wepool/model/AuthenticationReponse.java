package com.wepool.model;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationReponse {

	private String token;

	public AuthenticationReponse(String token) {
		super();
		this.token = token;
	}

	public AuthenticationReponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}

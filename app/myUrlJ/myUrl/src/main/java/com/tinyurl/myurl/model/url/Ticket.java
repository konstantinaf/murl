package com.tinyurl.myurl.model.url;

import java.io.Serializable;

public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1034496168821669608L;
	private String token;
	private String tokenSecret;
	private String redirectUrl;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}

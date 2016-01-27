package com.tinyurl.myurl.dto.url;

import com.tinyurl.myurl.model.url.Ticket;

public class TicketDTO {

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

	public static TicketDTO map(Ticket ticket) {
		TicketDTO dto = new TicketDTO();

		if (ticket != null) {
			dto.setToken(ticket.getToken());
			dto.setTokenSecret(ticket.getTokenSecret());
			dto.setRedirectUrl(ticket.getRedirectUrl());

		}

		return dto;
	}

}

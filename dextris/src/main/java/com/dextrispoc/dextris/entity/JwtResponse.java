package com.dextrispoc.dextris.entity;

public class JwtResponse {
	private String jwtToken;

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public JwtResponse() {
		
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}

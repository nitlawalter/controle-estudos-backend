package com.walter.api.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String senha;
	
	public JwtAuthenticationRequest() {
		
	}
	
	public JwtAuthenticationRequest(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}

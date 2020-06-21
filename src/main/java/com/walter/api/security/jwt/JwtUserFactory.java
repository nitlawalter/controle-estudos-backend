package com.walter.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.walter.api.entity.Usuario;

public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	
	public static JwtUser create(Usuario user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getSenha(), mapToGranteAuthorities(user.getPerfil()));
	}

	private static List<GrantedAuthority> mapToGranteAuthorities(String perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}
	
	

}

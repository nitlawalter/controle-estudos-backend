package com.walter.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Usuario;
import com.walter.api.security.jwt.JwtUserFactory;
import com.walter.api.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = usuarioService.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("usuario não encontrado '%s'.", email));
		}else {
			return JwtUserFactory.create(user);
		}
	}

	
}

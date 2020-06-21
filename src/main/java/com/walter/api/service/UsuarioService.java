package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Usuario;
import com.walter.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario findById(Long id) {
		Optional<Usuario> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Usuario findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Usuario salvar(Usuario d) {
		return repo.save(d);
	}
	
	public Usuario editar(Usuario d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

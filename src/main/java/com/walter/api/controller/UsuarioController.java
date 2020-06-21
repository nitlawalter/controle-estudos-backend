package com.walter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walter.api.entity.Usuario;
import com.walter.api.response.Response;
import com.walter.api.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> inserir(@RequestBody Usuario usuario, BindingResult result){
		
		Response<Usuario> response = new Response<Usuario>();
		try {
			validar(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			Usuario entity = service.salvar(usuario);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> editar(@RequestBody Usuario usuario, BindingResult result){
		
		Response<Usuario> response = new Response<Usuario>();
		try {
			validar(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			Usuario entity = service.salvar(usuario);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") Long id){
		Response<Usuario> response = new Response<Usuario>();
		Usuario entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Usuario não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/email/{email}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> findByEmail(@PathVariable("email") String email){
		Response<Usuario> response = new Response<Usuario>();
		Usuario entity = service.findByEmail(email);
		if(entity == null) {
			response.getErros().add("Usuario não encontrada email: " + email);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Usuario entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Usuario não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
		
	@GetMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<List<Usuario>>> findAll(){
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	


	private void validar(Usuario usuario, BindingResult result) {
		if(usuario.getEmail() == null) {
			result.addError(new ObjectError("Usuario", "email não informado"));
		}		
	}
}

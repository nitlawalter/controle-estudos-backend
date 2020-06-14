package com.walter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.walter.api.entity.Assunto;
import com.walter.api.response.Response;
import com.walter.api.service.AssuntoService;

@RestController
@RequestMapping("/api/assuntos")
@CrossOrigin(origins = "*")
public class AssuntoController {	
		
	@Autowired
	private AssuntoService service;
	
	@PostMapping
	public ResponseEntity<Response<Assunto>> inserir(@RequestBody Assunto assunto, BindingResult result){
		
		Response<Assunto> response = new Response<Assunto>();
		try {
			validar(assunto, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Assunto entity = service.salvar(assunto);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Assunto>> editar(@RequestBody Assunto assunto, BindingResult result){
		
		Response<Assunto> response = new Response<Assunto>();
		try {
			validar(assunto, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Assunto entity = service.salvar(assunto);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Assunto>> findById(@PathVariable("id") Long id){
		Response<Assunto> response = new Response<Assunto>();
		Assunto entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Assunto não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Assunto entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Assunto não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Assunto>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Assunto>> response = new Response<Page<Assunto>>();
		Page<Assunto> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Assunto>>> findAll(){
		Response<List<Assunto>> response = new Response<List<Assunto>>();
		List<Assunto> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	


	private void validar(Assunto assunto, BindingResult result) {
		if(assunto.getNome() == null) {
			result.addError(new ObjectError("Assunto", "Nome não informado"));
		}		
	}
}

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

import com.walter.api.entity.Topico;
import com.walter.api.response.Response;
import com.walter.api.service.TopicoService;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoController {
	
	@Autowired
	private TopicoService service;
	
	@PostMapping
	public ResponseEntity<Response<Topico>> inserir(@RequestBody Topico topico, BindingResult result){
		
		Response<Topico> response = new Response<Topico>();
		try {
			validar(topico, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Topico entity = service.salvar(topico);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Topico>> editar(@RequestBody Topico topico, BindingResult result){
		
		Response<Topico> response = new Response<Topico>();
		try {
			validar(topico, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Topico entity = service.salvar(topico);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Topico>> findById(@PathVariable("id") Long id){
		Response<Topico> response = new Response<Topico>();
		Topico entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Topico não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Topico entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Topico não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Topico>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Topico>> response = new Response<Page<Topico>>();
		Page<Topico> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Topico>>> findAll(){
		Response<List<Topico>> response = new Response<List<Topico>>();
		List<Topico> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping(value = "/assunto/{id}")
	public ResponseEntity<Response<List<Topico>>> findByAssuntoId(@PathVariable("id") Long id){
		Response<List<Topico>> response = new Response<List<Topico>>();
		List<Topico> lista = service.findByAssuntoId(id);
		if(lista == null) {
			response.getErros().add("Lista de Tópicos não encontrada pelo id do assunto: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(lista);
		return ResponseEntity.ok(response);
	}


	private void validar(Topico topico, BindingResult result) {
		if(topico.getNome() == null) {
			result.addError(new ObjectError("Topico", "Nome não informado"));
		}		
	}
}

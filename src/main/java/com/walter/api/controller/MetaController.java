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

import com.walter.api.entity.Meta;
import com.walter.api.response.Response;
import com.walter.api.service.MetaService;

@RestController
@RequestMapping("/api/metas")
@CrossOrigin(origins = "*")
public class MetaController {
	
	@Autowired
	private MetaService service;
	
	@PostMapping
	public ResponseEntity<Response<Meta>> inserir(@RequestBody Meta disciplina, BindingResult result){
		
		Response<Meta> response = new Response<Meta>();
		try {
			validar(disciplina, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Meta entity = service.salvar(disciplina);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Meta>> editar(@RequestBody Meta disciplina, BindingResult result){
		
		Response<Meta> response = new Response<Meta>();
		try {
			validar(disciplina, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Meta entity = service.salvar(disciplina);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Meta>> findById(@PathVariable("id") Long id){
		Response<Meta> response = new Response<Meta>();
		Meta entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Meta não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Meta entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Meta não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Meta>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Meta>> response = new Response<Page<Meta>>();
		Page<Meta> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Meta>>> findAll(){
		Response<List<Meta>> response = new Response<List<Meta>>();
		List<Meta> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping(value =  "/dia/{dia}")
	public ResponseEntity<Response<List<Meta>>> findBydia(@PathVariable("dia") String dia){
		Response<List<Meta>> response = new Response<List<Meta>>();
		List<Meta> lista = service.findBydia(dia);
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	


	private void validar(Meta disciplina, BindingResult result) {
		if(disciplina.getNome() == null) {
			result.addError(new ObjectError("Meta", "Nome não informado"));
		}		
	}
}

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

import com.walter.api.entity.Disciplina;
import com.walter.api.response.Response;
import com.walter.api.service.DisciplinaService;

@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService service;
	
	@PostMapping
	public ResponseEntity<Response<Disciplina>> inserir(@RequestBody Disciplina disciplina, BindingResult result){
		
		Response<Disciplina> response = new Response<Disciplina>();
		try {
			validar(disciplina, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Disciplina entity = service.salvar(disciplina);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Disciplina>> editar(@RequestBody Disciplina disciplina, BindingResult result){
		
		Response<Disciplina> response = new Response<Disciplina>();
		try {
			validar(disciplina, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Disciplina entity = service.salvar(disciplina);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Disciplina>> findById(@PathVariable("id") Long id){
		Response<Disciplina> response = new Response<Disciplina>();
		Disciplina entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Disciplina não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Disciplina entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Disciplina não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Disciplina>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Disciplina>> response = new Response<Page<Disciplina>>();
		Page<Disciplina> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Disciplina>>> findAll(){
		Response<List<Disciplina>> response = new Response<List<Disciplina>>();
		List<Disciplina> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	


	private void validar(Disciplina disciplina, BindingResult result) {
		if(disciplina.getNome() == null) {
			result.addError(new ObjectError("Disciplina", "Nome não informado"));
		}		
	}
}

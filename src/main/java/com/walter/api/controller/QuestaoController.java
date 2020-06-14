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

import com.walter.api.entity.Questao;
import com.walter.api.response.Response;
import com.walter.api.service.QuestaoService;

@RestController
@RequestMapping("/api/questoes")
@CrossOrigin(origins = "*")
public class QuestaoController {
	
	@Autowired
	private QuestaoService service;
	
	@PostMapping
	public ResponseEntity<Response<Questao>> inserir(@RequestBody Questao questao, BindingResult result){
		
		Response<Questao> response = new Response<Questao>();
		try {
			validar(questao, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Questao entity = service.salvar(questao);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Questao>> editar(@RequestBody Questao questao, BindingResult result){
		
		Response<Questao> response = new Response<Questao>();
		try {
			validar(questao, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Questao entity = service.salvar(questao);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Questao>> findById(@PathVariable("id") Long id){
		Response<Questao> response = new Response<Questao>();
		Questao entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Questao não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Questao entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Questao não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Questao>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Questao>> response = new Response<Page<Questao>>();
		Page<Questao> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Questao>>> findAll(){
		Response<List<Questao>> response = new Response<List<Questao>>();
		List<Questao> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	


	private void validar(Questao questao, BindingResult result) {
		if(questao.getQuestao() == null) {
			result.addError(new ObjectError("Questao", "Questão não informada"));
		}		
	}
}

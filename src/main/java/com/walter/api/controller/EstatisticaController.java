package com.walter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walter.api.entity.Estatistica;
import com.walter.api.response.Response;
import com.walter.api.service.EstatisticaService;

@RestController
@RequestMapping("/api/estatisticas")
@CrossOrigin(origins = "*")
public class EstatisticaController {
	
	@Autowired
	private EstatisticaService service;
	
	@PostMapping
	public ResponseEntity<Response<Estatistica>> inserir(@RequestBody Estatistica estatistica, BindingResult result){
		
		Response<Estatistica> response = new Response<Estatistica>();
		try {
			validar(estatistica, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Estatistica entity = service.salvar(estatistica);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Estatistica>> editar(@RequestBody Estatistica estatistica, BindingResult result){
		
		Response<Estatistica> response = new Response<Estatistica>();
		try {
			validar(estatistica, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Estatistica entity = service.salvar(estatistica);
			response.setData(entity);
		}catch(Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Estatistica>> findById(@PathVariable("id") Long id){
		Response<Estatistica> response = new Response<Estatistica>();
		Estatistica entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Estatistica não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entity);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		Response<String> response = new Response<String>();
		Estatistica entity = service.findById(id);
		if(entity == null) {
			response.getErros().add("Estatistica não encontrada id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value =  "{page}/{size}")
	public ResponseEntity<Response<Page<Estatistica>>> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
		Response<Page<Estatistica>> response = new Response<Page<Estatistica>>();
		Page<Estatistica> listaPage = service.findPage(page, size);
		response.setData(listaPage);
		return ResponseEntity.ok(response);				
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<Estatistica>>> findAll(){
		Response<List<Estatistica>> response = new Response<List<Estatistica>>();
		List<Estatistica> lista = service.findAll();
		response.setData(lista);
		return ResponseEntity.ok(response);				
	}
	
	


	private void validar(Estatistica estatistica, BindingResult result) {
				
	}
}

package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Assunto;
import com.walter.api.repository.AssuntoRepository;

@Service
public class AssuntoService {
	
	@Autowired
	private AssuntoRepository repo;
	
	public Assunto findById(Long id) {
		Optional<Assunto> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Assunto> findAll() {
		return repo.findAll();
	}
	
	public Page<Assunto> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Assunto> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Assunto salvar(Assunto d) {
		return repo.save(d);
	}
	
	public Assunto editar(Assunto d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

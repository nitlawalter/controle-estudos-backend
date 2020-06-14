package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Disciplina;
import com.walter.api.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository repo;
	
	public Disciplina findById(Long id) {
		Optional<Disciplina> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Disciplina> findAll() {
		return repo.findAll();
	}
	
	public Page<Disciplina> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Disciplina> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Disciplina salvar(Disciplina d) {
		return repo.save(d);
	}
	
	public Disciplina editar(Disciplina d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

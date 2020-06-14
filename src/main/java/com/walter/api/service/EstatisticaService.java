package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Estatistica;
import com.walter.api.repository.EstatisticaRepository;

@Service
public class EstatisticaService {
	
	@Autowired
	private EstatisticaRepository repo;
	
	public Estatistica findById(Long id) {
		Optional<Estatistica> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Estatistica> findAll() {
		return repo.findAll();
	}
	
	public Page<Estatistica> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Estatistica> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Estatistica salvar(Estatistica d) {
		return repo.save(d);
	}
	
	public Estatistica editar(Estatistica d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

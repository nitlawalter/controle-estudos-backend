package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Meta;
import com.walter.api.repository.MetaRepository;

@Service
public class MetaService {
	
	@Autowired
	private MetaRepository repo;
	
	public Meta findById(Long id) {
		Optional<Meta> entity = repo.findById(id);
		return entity.get();
	}
	

	public List<Meta> findAll() {
		return repo.findAll();
	}
	
	public List<Meta> findBydia(String dia) {
		return repo.findByDia(dia);
	}
	
	public Page<Meta> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Meta> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Meta salvar(Meta d) {
		return repo.save(d);
	}
	
	public Meta editar(Meta d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Topico;
import com.walter.api.repository.TopicoRepository;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository repo;
	
	public Topico findById(Long id) {
		Optional<Topico> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Topico> findAll() {
		return repo.findAllByOrderByAssuntoDisciplinaNomeAsc();
	}
	
	public List<Topico> findByAssuntoId(Long id) {
		List<Topico> lista = repo.findByAssuntoIdOrderByAssuntoNomeAsc(id);
		return lista;
	}
	
	public List<Topico> findByAssuntoDisciplinaId(Long id) {
		List<Topico> lista = repo.findByAssuntoDisciplinaIdOrderByAssuntoNomeAsc(id);
		return lista;
	}
	
	public Page<Topico> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Topico> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Topico salvar(Topico d) {
		return repo.save(d);
	}
	
	public Topico editar(Topico d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

package com.walter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.walter.api.entity.Questao;
import com.walter.api.repository.QuestaoRepository;

@Service
public class QuestaoService {
	
	@Autowired
	private QuestaoRepository repo;
	
	public Questao findById(Long id) {
		Optional<Questao> entity = repo.findById(id);
		return entity.get();
	}
	
	public List<Questao> findAll() {
		return repo.findAll();
	}
	
	public List<Questao> findByTopicoAssuntoId(Long id) {		
		return repo.findByTopicoAssuntoId(id);
	}
	
	public List<Questao> findByTopicoAssuntoIdAndUsuarioId(Long idAssunto, Long idUsuario) {		
		return repo.findByTopicoAssuntoIdAndUsuarioId(idAssunto, idUsuario);
	}
	
	public List<Questao> findByTopicoAssuntoDisciplinaId(Long id) {		
		return repo.findByTopicoAssuntoDisciplinaId(id);
	}
	
	public List<Questao> findByTopicoAssuntoDisciplinaIdAndUsuarioId(Long idDisciplina, Long idUsuario) {		
		return repo.findByTopicoAssuntoDisciplinaIdAndUsuarioId(idDisciplina, idUsuario);
	}
	
	public Page<Questao> findPage(Integer pagina, Integer linhaPorPagina, String orderBy, String ascOrDesc) {
		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(ascOrDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Questao> findPage(int page, int size){
		PageRequest pageble = PageRequest.of(page, size);
		return repo.findAll(pageble);
	}
	
	public Questao salvar(Questao d) {
		return repo.save(d);
	}
	
	public Questao editar(Questao d) {
		return repo.save(d);		
	}
	
	public void deletar(Long id) {
		repo.deleteById(id);
	}

}

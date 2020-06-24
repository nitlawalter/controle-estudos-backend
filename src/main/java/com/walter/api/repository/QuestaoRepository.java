package com.walter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
	
	List<Questao> findByTopicoAssuntoId(Long id);
	
	List<Questao> findByTopicoAssuntoIdAndUsuarioId(Long idAssunto, Long idUsuario);
	
	List<Questao> findByTopicoAssuntoDisciplinaId(Long id);
	
	List<Questao> findByTopicoAssuntoDisciplinaIdAndUsuarioId(Long idDisciplina, Long idUsuario);

}

package com.walter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {
	
	List<Assunto> findByDisciplinaIdOrderByNomeAsc(Long id);

}

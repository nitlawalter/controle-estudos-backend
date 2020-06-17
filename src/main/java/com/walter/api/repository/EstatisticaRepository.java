package com.walter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Estatistica;

@Repository
public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {
	
	List<Estatistica> findByAssuntoId(Long id);
	
	List<Estatistica> findByAssuntoDisciplinaId(Long id);

}

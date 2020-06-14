package com.walter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Estatistica;

@Repository
public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {

}

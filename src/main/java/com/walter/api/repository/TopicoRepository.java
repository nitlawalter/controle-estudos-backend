package com.walter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

}

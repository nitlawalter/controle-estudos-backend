package com.walter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Meta;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
	
	List<Meta> findByDia(String dia);
}

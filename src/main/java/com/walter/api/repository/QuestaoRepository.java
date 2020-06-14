package com.walter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walter.api.entity.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}

package br.uea.edu.atividade_9_aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uea.edu.atividade_9_aps.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}

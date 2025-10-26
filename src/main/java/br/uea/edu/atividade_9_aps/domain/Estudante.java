package br.uea.edu.atividade_9_aps.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer matricula;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "estudante_turma",
        joinColumns = @JoinColumn(name="estudante_id"),
        inverseJoinColumns = @JoinColumn(name="turma_id")
    )
    private List<Turma> turmas;
}

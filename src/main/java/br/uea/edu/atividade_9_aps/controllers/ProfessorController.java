package br.uea.edu.atividade_9_aps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uea.edu.atividade_9_aps.domain.Professor;
import br.uea.edu.atividade_9_aps.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores(){
        return ResponseEntity.ok(professorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorCodigo(@PathVariable Integer id){
        return professorService.buscarPorCodigo(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor){
        Professor professorSalva = professorService.salvar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Integer id, @RequestBody Professor professor){
        if (!professorService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Professor professorSalva = professorService.atualizar(id, professor);
        return ResponseEntity.status(HttpStatus.OK).body(professorSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id){
        if (!professorService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        professorService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}   

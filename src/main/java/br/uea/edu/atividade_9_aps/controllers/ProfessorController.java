package br.uea.edu.atividade_9_aps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uea.edu.atividade_9_aps.domain.Professor;
import br.uea.edu.atividade_9_aps.dto.ProfessorDTO;
import br.uea.edu.atividade_9_aps.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<ProfessorDTO>> listarProfessores(){
        return ResponseEntity.ok(professorService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ProfessorDTO> buscarPorCodigo(@PathVariable Integer id){
        return professorService.buscarPorCodigo(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfessorDTO> salvar(@RequestBody Professor professor){
        ProfessorDTO professorSalva = professorService.salvar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalva);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfessorDTO> atualizar(@PathVariable Integer id, @RequestBody Professor professor){
        if (!professorService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ProfessorDTO professorSalva = professorService.atualizar(id, professor);
        return ResponseEntity.status(HttpStatus.OK).body(professorSalva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if (!professorService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        professorService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}   

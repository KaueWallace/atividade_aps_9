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

import br.uea.edu.atividade_9_aps.domain.Turma;
import br.uea.edu.atividade_9_aps.dto.TurmaDTO;
import br.uea.edu.atividade_9_aps.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<TurmaDTO>> listarTurmas(){
        return ResponseEntity.ok(turmaService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<TurmaDTO> buscarPorCodigo(@PathVariable Integer id){
        return turmaService.buscarPorCodigo(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TurmaDTO> salvar(@RequestBody Turma turma){
        TurmaDTO turmaSalva = turmaService.salvar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TurmaDTO> atualizar(@PathVariable Integer id, @RequestBody Turma turma){
        if (!turmaService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        TurmaDTO turmaSalva = turmaService.atualizar(id, turma);
        return ResponseEntity.status(HttpStatus.OK).body(turmaSalva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if (!turmaService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        turmaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}   

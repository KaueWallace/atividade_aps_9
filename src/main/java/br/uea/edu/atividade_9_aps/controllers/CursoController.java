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

import br.uea.edu.atividade_9_aps.domain.Curso;
import br.uea.edu.atividade_9_aps.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(){
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorCodigo(@PathVariable Integer id){
        return cursoService.buscarPorCodigo(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso){
        Curso cursoSalva = cursoService.salvar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Integer id, @RequestBody Curso curso){
        if (!cursoService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Curso cursoSalva = cursoService.atualizar(id, curso);
        return ResponseEntity.status(HttpStatus.OK).body(cursoSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id){
        if (!cursoService.buscarPorCodigo(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        cursoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}   

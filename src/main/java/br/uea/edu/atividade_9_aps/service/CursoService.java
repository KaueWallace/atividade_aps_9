package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Curso;
import br.uea.edu.atividade_9_aps.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listar(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorCodigo(Integer id){
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public void deletar(Integer id){
        cursoRepository.deleteById(id);
    }

    public Curso atualizar(Integer id, Curso novoCurso){
        Curso cursoExistente = this.buscarPorCodigo(id).orElseThrow(
            () -> new RuntimeException("Curso não encontrado")
        );

        cursoExistente.setNome(novoCurso.getNome());
        cursoExistente.setCodigoCurso(novoCurso.getCodigoCurso()); 

        return cursoRepository.save(cursoExistente);
    }
}

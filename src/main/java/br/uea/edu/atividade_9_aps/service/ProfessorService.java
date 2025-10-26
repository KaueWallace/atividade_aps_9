package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Professor;
import br.uea.edu.atividade_9_aps.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listar(){
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorCodigo(Integer id){
        return professorRepository.findById(id);
    }

    public Professor salvar(Professor professor){
        return professorRepository.save(professor);
    }

    public void deletar(Integer id){
        professorRepository.deleteById(id);
    }

    public Professor atualizar(Integer id, Professor novoProfessor){
        Professor professorExistente = this.buscarPorCodigo(id).orElseThrow(
            () -> new RuntimeException("Professor não encontrado")
        );

        professorExistente.setNome(novoProfessor.getNome());
        professorExistente.setEspecialidade(novoProfessor.getEspecialidade()); 

        return professorRepository.save(professorExistente);
    }
}

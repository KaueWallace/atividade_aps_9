package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Professor;
import br.uea.edu.atividade_9_aps.dto.ProfessorDTO;
import br.uea.edu.atividade_9_aps.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorDTO> listar(){
        return professorRepository.findAll().stream()
        .map(professor -> {
            ProfessorDTO dto = new ProfessorDTO();
            dto.setId(professor.getId());
            dto.setNome(professor.getNome());
            dto.setEspecialidade(professor.getEspecialidade());
            return dto; 
        })
        .collect(Collectors.toList());
    }

    public Optional<ProfessorDTO> buscarPorCodigo(Integer id) {
        return professorRepository.findById(id)
            .map(professor -> {
                ProfessorDTO dto = new ProfessorDTO();
                dto.setId(professor.getId());
                dto.setNome(professor.getNome());
                dto.setEspecialidade(professor.getEspecialidade());
                return dto;
        });
    }


    public ProfessorDTO salvar(Professor professor){        
        Professor salvo = professorRepository.save(professor);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(salvo.getId());
        professorDTO.setNome(salvo.getNome());
        professorDTO.setEspecialidade(salvo.getEspecialidade());

        return professorDTO;
    }

    public void deletar(Integer id){
        professorRepository.deleteById(id);
    }

    public ProfessorDTO atualizar(Integer id, Professor novoProfessor){
        Professor professorExistente = professorRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Professor não encontrado")
        );

        professorExistente.setNome(novoProfessor.getNome());
        professorExistente.setEspecialidade(novoProfessor.getEspecialidade()); 

        Professor atualizado = professorRepository.save(professorExistente);

        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(atualizado.getId());
        dto.setNome(atualizado.getNome());
        dto.setEspecialidade(atualizado.getEspecialidade());

        return dto;
    }
}

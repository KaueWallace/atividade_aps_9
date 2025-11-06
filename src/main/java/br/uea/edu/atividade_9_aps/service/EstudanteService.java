package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Estudante;
import br.uea.edu.atividade_9_aps.dto.EstudanteDTO;
import br.uea.edu.atividade_9_aps.repository.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<EstudanteDTO> listar(){
        return estudanteRepository.findAll().stream()
        .map(estudante -> {
            EstudanteDTO dto = new EstudanteDTO();
            dto.setId(estudante.getId());
            dto.setNome(estudante.getNome());
            dto.setMatricula(estudante.getMatricula());
            return dto; 
        })
        .collect(Collectors.toList());
    }

    public Optional<EstudanteDTO> buscarPorCodigo(Integer id) {
        return estudanteRepository.findById(id)
            .map(estudante -> {
                EstudanteDTO dto = new EstudanteDTO();
                dto.setId(estudante.getId());
                dto.setNome(estudante.getNome());
                dto.setMatricula(estudante.getMatricula());
                return dto;
        });
    }

     public EstudanteDTO salvar(Estudante estudante){        
        Estudante salvo = estudanteRepository.save(estudante);

        EstudanteDTO estudanteDTO = new EstudanteDTO();
        estudanteDTO.setId(salvo.getId());
        estudanteDTO.setNome(salvo.getNome());
        estudanteDTO.setMatricula(salvo.getMatricula());

        return estudanteDTO;
    }

    public void deletar(Integer id){
        estudanteRepository.deleteById(id);
    }

    public EstudanteDTO atualizar(Integer id, Estudante novoEstudante){
        Estudante estudanteExistente = estudanteRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Estudante não encontrado")
        );

        estudanteExistente.setNome(novoEstudante.getNome());
        estudanteExistente.setMatricula(novoEstudante.getMatricula()); 

        Estudante atualizado = estudanteRepository.save(estudanteExistente);

        EstudanteDTO dto = new EstudanteDTO();
        dto.setId(atualizado.getId());
        dto.setNome(atualizado.getNome());
        dto.setMatricula(atualizado.getMatricula());

        return dto;
    }
}

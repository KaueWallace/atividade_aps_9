package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Turma;
import br.uea.edu.atividade_9_aps.dto.TurmaDTO;
import br.uea.edu.atividade_9_aps.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<TurmaDTO> listar(){
        return turmaRepository.findAll().stream()
        .map(turma -> {
            TurmaDTO dto = new TurmaDTO();
            dto.setId(turma.getId());
            dto.setCodigoTurma(turma.getCodigoTurma());
            dto.setHorario(turma.getHorario());
            return dto; 
        })
        .collect(Collectors.toList());
    }

    public Optional<TurmaDTO> buscarPorCodigo(Integer id) {
        return turmaRepository.findById(id)
            .map(turma -> {
                TurmaDTO dto = new TurmaDTO();
                dto.setId(turma.getId());
                dto.setCodigoTurma(turma.getCodigoTurma());
                dto.setHorario(turma.getHorario());
                return dto;
        });
    }

    public TurmaDTO salvar(Turma turma){        
        Turma salvo = turmaRepository.save(turma);

        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(salvo.getId());
        turmaDTO.setCodigoTurma(salvo.getCodigoTurma());
        turmaDTO.setHorario(salvo.getHorario());

        return turmaDTO;
    }

    public void deletar(Integer id){
        turmaRepository.deleteById(id);
    }

    public TurmaDTO atualizar(Integer id, Turma novoTurma){
        Turma turmaExistente = turmaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Turma não encontrada")
        );

        turmaExistente.setCodigoTurma(novoTurma.getCodigoTurma());
        turmaExistente.setHorario(novoTurma.getHorario()); 

        Turma atualizado = turmaRepository.save(turmaExistente);

        TurmaDTO dto = new TurmaDTO();
        dto.setId(atualizado.getId());
        dto.setCodigoTurma(atualizado.getCodigoTurma());
        dto.setHorario(atualizado.getHorario());

        return dto;
    }
}

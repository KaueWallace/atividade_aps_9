package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Turma;
import br.uea.edu.atividade_9_aps.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> listar(){
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorCodigo(Integer id){
        return turmaRepository.findById(id);
    }

    public Turma salvar(Turma turma){
        return turmaRepository.save(turma);
    }

    public void deletar(Integer id){
        turmaRepository.deleteById(id);
    }

    public Turma atualizar(Integer id, Turma novoTurma){
        Turma turmaExistente = this.buscarPorCodigo(id).orElseThrow(
            () -> new RuntimeException("Turma não encontrada")
        );

        turmaExistente.setCodigoTurma(novoTurma.getCodigoTurma());
        turmaExistente.setHorario(novoTurma.getHorario()); 

        return turmaRepository.save(turmaExistente);
    }
}

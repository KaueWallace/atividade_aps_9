package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Estudante;
import br.uea.edu.atividade_9_aps.repository.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Estudante> listar(){
        return estudanteRepository.findAll();
    }

    public Optional<Estudante> buscarPorCodigo(Integer id){
        return estudanteRepository.findById(id);
    }

    public Estudante salvar(Estudante estudante){
        return estudanteRepository.save(estudante);
    }

    public void deletar(Integer id){
        estudanteRepository.deleteById(id);
    }

    public Estudante atualizar(Integer id, Estudante novoEstudante){
        Estudante estudanteExistente = this.buscarPorCodigo(id).orElseThrow(
            () -> new RuntimeException("Estudante não encontrado")
        );

        estudanteExistente.setNome(novoEstudante.getNome());
        estudanteExistente.setMatricula(novoEstudante.getMatricula()); 

        return estudanteRepository.save(estudanteExistente);
    }
}

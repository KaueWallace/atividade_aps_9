package br.uea.edu.atividade_9_aps.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uea.edu.atividade_9_aps.domain.Curso;
import br.uea.edu.atividade_9_aps.dto.CursoDTO;

import br.uea.edu.atividade_9_aps.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> listar(){
        return cursoRepository.findAll().stream()
        .map(curso -> {
            CursoDTO dto = new CursoDTO();
            dto.setId(curso.getId());
            dto.setNome(curso.getNome());
            dto.setCodigoCurso(curso.getCodigoCurso());
            return dto; 
        })
        .collect(Collectors.toList());
    }

    public Optional<CursoDTO> buscarPorCodigo(Integer id){
        return cursoRepository.findById(id)
            .map(curso -> {
                CursoDTO dto = new CursoDTO();
                dto.setId(curso.getId());
                dto.setNome(curso.getNome());
                dto.setCodigoCurso(curso.getCodigoCurso());
                return dto;
        });
    }

    public CursoDTO salvar(Curso curso){        
        Curso salvo = cursoRepository.save(curso);

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(salvo.getId());
        cursoDTO.setNome(salvo.getNome());
        cursoDTO.setCodigoCurso(salvo.getCodigoCurso());

        return cursoDTO;
    }

    public void deletar(Integer id){
        cursoRepository.deleteById(id);
    }

    public CursoDTO atualizar(Integer id, Curso novoCurso){
        Curso cursoExistente = cursoRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Curso não encontrado")
        );

        cursoExistente.setNome(novoCurso.getNome());
        cursoExistente.setCodigoCurso(novoCurso.getCodigoCurso()); 

        Curso atualizado = cursoRepository.save(cursoExistente);


        CursoDTO dto = new CursoDTO();
        dto.setId(atualizado.getId());
        dto.setNome(atualizado.getNome());
        dto.setCodigoCurso(atualizado.getCodigoCurso());

        return dto;
    }
}

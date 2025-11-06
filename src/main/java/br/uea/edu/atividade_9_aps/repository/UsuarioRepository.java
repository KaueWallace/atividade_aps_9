package br.uea.edu.atividade_9_aps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uea.edu.atividade_9_aps.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}

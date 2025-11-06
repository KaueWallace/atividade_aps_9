package br.uea.edu.atividade_9_aps.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.uea.edu.atividade_9_aps.security.jwt.JwtTokenProvider;
import br.uea.edu.atividade_9_aps.service.UsuarioService;
import br.uea.edu.atividade_9_aps.domain.Usuario;
import br.uea.edu.atividade_9_aps.dto.TokenDTO;
import br.uea.edu.atividade_9_aps.dto.UserLoginDTO;
import br.uea.edu.atividade_9_aps.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody UserRegisterDTO request) {
        Usuario usuario = usuarioService.registrarUsuario(request.getUsername(), request.getPassword(), "ROLE_USER");
        return ResponseEntity.ok(usuario);
    }
}

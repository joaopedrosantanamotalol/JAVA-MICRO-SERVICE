package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.application.dto.LoginRequest;
import com.usuario.usuario.base.application.dto.LoginResponse;
import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.infra.config.PasswordEncoder;

public class LoginUsuarioUseCase {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginUsuarioUseCase(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponse executar(LoginRequest request) {

        UsuarioEntity usuario = repository
                .acharPorEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("Email ou senha inválidos")
                );

        if (!passwordEncoder.matches(
                request.senha(),
                usuario.getSenha()
        )) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        String accessToken =
                tokenService.generateAccessToken(usuario);

        String refreshToken =
                tokenService.generateRefreshToken(usuario);

        return new LoginResponse(
                accessToken,
                refreshToken
        );
    }
}
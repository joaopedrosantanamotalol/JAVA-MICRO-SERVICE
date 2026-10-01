package com.usuario.usuario.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.application.usecases.RefreshTokenUseCase;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;

@Configuration 
public class BeanConfig {

    @Bean 
    public RefreshTokenUseCase refreshTokenUseCase(
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) {
        return new RefreshTokenUseCase(
                tokenService,
                usuarioRepository
        );
    }
}
package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;

public class RefreshTokenUseCase {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public RefreshTokenUseCase(
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    public String executar(String refreshToken) {

        if (refreshToken == null
                || refreshToken.isBlank()
                || !tokenService.validateRefreshToken(refreshToken)) {
            throw new RefreshTokenInvalidoException(
                    "Refresh token inválido ou expirado"
            );
        }

        String email = tokenService.getEmailFromToken(refreshToken);

        UsuarioEntity usuario = usuarioRepository
                .acharPorEmail(email)
                .orElseThrow(() ->
                        new RefreshTokenInvalidoException(
                                "Usuário não encontrado"
                        )
                );

        return tokenService.generateAccessToken(usuario);
    }
}
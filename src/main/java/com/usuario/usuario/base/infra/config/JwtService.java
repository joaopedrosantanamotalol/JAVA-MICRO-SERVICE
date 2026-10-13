package com.usuario.usuario.base.infra.config;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface JwtService {
    String gerarAccessToken(UsuarioEntity usuario);

    String gerarRefreshToken(UsuarioEntity usuario);

    boolean isTokenValid(String token);

    String extractUsername(String token);
}

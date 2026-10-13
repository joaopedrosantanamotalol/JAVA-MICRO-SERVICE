package com.usuario.usuario.base.application.ports;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface TokenService {
    String generateAccessToken(UsuarioEntity usuario);

    String generateRefreshToken(UsuarioEntity usuario);

    void revokeToken(String token);

    boolean isTokenRevoked(String token);

    boolean validateAccessToken(String token);

    boolean validateRefreshToken(String token);
    
    String getEmailFromToken(String token);
}

package com.usuario.usuario.base.application.ports;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface TokenService {
    String generateAccessToken(UsuarioEntity usuario);

    String generateRefreshToken(UsuarioEntity usuario);

    boolean validateRefreshToken(String token);
    
    String getEmailFromToken(String token);
}

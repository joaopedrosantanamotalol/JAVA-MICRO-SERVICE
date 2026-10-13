package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.infra.config.CookieService;

public class LogoutUseCase {

    private final CookieService cookieService;
    private final TokenService tokenService;

    public LogoutUseCase(
            CookieService cookieService,
            TokenService tokenService
    ) {
        this.cookieService = cookieService;
        this.tokenService = tokenService;
    }

    public void executar(String accessToken, String refreshToken) {
        if (accessToken != null && !accessToken.isBlank()) {
            tokenService.revokeToken(accessToken);
        }

        if (refreshToken != null && !refreshToken.isBlank()) {
            tokenService.revokeToken(refreshToken);
        }

        cookieService.removerToken();
    }
}
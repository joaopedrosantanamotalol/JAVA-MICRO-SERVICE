package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.infra.config.CookieService;

public class LogoutUseCase {

    private final CookieService cookieService;

    public LogoutUseCase(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    public void executar() {
        cookieService.removerToken();
    }
}
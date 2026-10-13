package com.usuario.usuario.base.application.usecases;

public class RefreshTokenInvalidoException extends RuntimeException {

    public RefreshTokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}
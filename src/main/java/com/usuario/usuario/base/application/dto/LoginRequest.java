package com.usuario.usuario.base.application.dto;

public record LoginRequest(
    String email,
    String senha
) {}

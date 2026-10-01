package com.usuario.usuario.base.application.dto;

public record LoginResponse(
    String acesstoken,
    String refreshtoken
) {}

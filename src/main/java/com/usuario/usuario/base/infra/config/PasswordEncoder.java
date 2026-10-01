package com.usuario.usuario.base.infra.config;

public interface PasswordEncoder {
    boolean matches(String senha, String senhaCriptografada);
    String encode(String senha);
}

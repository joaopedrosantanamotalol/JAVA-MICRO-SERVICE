
package com.usuario.usuario.base.infra.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @Override
    public boolean matches(String senha, String senhaCriptografada) {
        return encoder.matches(senha, senhaCriptografada);
    }

    @Override
    public String encode(String senha) {
        return encoder.encode(senha);
    }
}

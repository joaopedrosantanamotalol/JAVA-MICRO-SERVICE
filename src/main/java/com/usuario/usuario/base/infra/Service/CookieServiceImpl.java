package com.usuario.usuario.base.infra.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.usuario.usuario.base.infra.config.CookieService;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieServiceImpl implements CookieService {

    private final HttpServletResponse response;

    public CookieServiceImpl(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void removerToken() {

        ResponseCookie cookie = ResponseCookie
                .from("token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );
    }
}
package com.usuario.usuario.base.presentation.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.usuario.base.application.dto.LoginRequest;
import com.usuario.usuario.base.application.dto.LoginResponse;
import com.usuario.usuario.base.application.dto.RefreshTokenRequest;
import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.application.usecases.LoginUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.LogoutUseCase;
import com.usuario.usuario.base.application.usecases.RefreshTokenUseCase;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;
import com.usuario.usuario.base.presentation.cookie.AuthCookieFactory;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

@RestController 
@RequestMapping("/auth")
public class AuthController {

    private final LoginUsuarioUseCase login;
    private final LogoutUseCase logout;
    private final UsuarioMapper mapper;
    private final RefreshTokenUseCase refreshToken;
    private final AuthCookieFactory cookieFactory;

    public AuthController(
            LoginUsuarioUseCase login,
            LogoutUseCase logout,
            RefreshTokenUseCase refreshToken,
            UsuarioMapper mapper,
            AuthCookieFactory cookieFactory
    ) {
        this.login = login;
        this.logout = logout;
        this.refreshToken = refreshToken;
        this.mapper = mapper;
        this.cookieFactory = cookieFactory;
    }

    @PostMapping
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest entity){
        return mapper.requestToResponse(entity);
    }

   @PostMapping("/login")
    public ResponseEntity<Void> efetuarLogin(
            @RequestBody LoginRequest entity
    ) {
        LoginResponse response = login.executar(entity);

        ResponseCookie accessCookie =
                cookieFactory.accessToken(response.acesstoken());

        ResponseCookie refreshCookie =
                cookieFactory.refreshToken(response.refreshtoken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .build();
    }

    @PostMapping("/logout")
        public ResponseEntity<Void> logout(HttpServletRequest request) {
        String accessToken = null;
        String refreshToken = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
                for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                        accessToken = cookie.getValue();
                }

                if ("refreshToken".equals(cookie.getName())) {
                        refreshToken = cookie.getValue();
                }
                }
        }

        String authorization = request.getHeader("Authorization");

        if (accessToken == null
                && authorization != null
                && authorization.startsWith("Bearer ")) {
                accessToken = authorization.substring(7);
        }

        logout.executar(accessToken, refreshToken);

        return ResponseEntity.noContent()
                .header(
                        HttpHeaders.SET_COOKIE,
                        cookieFactory.limparAccessToken().toString()
                )
                .header(
                        HttpHeaders.SET_COOKIE,
                        cookieFactory.limparRefreshToken().toString()
                )
                .build();
        }

        @PostMapping("/refresh")
        public ResponseEntity<LoginResponse> refresh(
                @RequestBody RefreshTokenRequest request
        ) {
                String accessToken = refreshToken.executar(request.refreshToken());

                return ResponseEntity.ok(
                        new LoginResponse(
                                accessToken,
                                request.refreshToken()
                        )
                );
        }
    
}

package com.usuario.usuario.base.presentation.controller;

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

@RestController 
@RequestMapping("/auth")
public class AuthController {

    private final LoginUsuarioUseCase login;
    private final LogoutUseCase logout;
    private final UsuarioMapper mapper;
    private final RefreshTokenUseCase refreshToken;


    public AuthController(
            LoginUsuarioUseCase login,
            LogoutUseCase logout,
            RefreshTokenUseCase refreshToken,
            UsuarioMapper mapper
    ) {
        this.login = login;
        this.logout = logout;
        this.refreshToken = refreshToken;
        this.mapper = mapper;
    }

    @PostMapping
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest entity){
        return mapper.requestToResponse(entity);
    }

    @PostMapping("/login")
    public LoginResponse efetuarLogin(@RequestBody  LoginRequest entity){
        return login.executar(entity);
    }

    @PostMapping("/logout")
    public void logout() {

        logout.executar();

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


package com.usuario.usuario.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.application.usecases.CriarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.DeletarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.EditarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.ListarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.LoginUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.LogoutUseCase;
import com.usuario.usuario.base.application.usecases.RefreshTokenUseCase;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.infra.config.CookieService;
import com.usuario.usuario.base.infra.config.PasswordEncoder;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;

@Configuration
public class BeanConfig {

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(
            UsuarioMapper mapper,
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder) {
        return new CriarUsuarioUseCase(mapper, repository, passwordEncoder);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(
            UsuarioRepository repository
    ) {
        return new DeletarUsuarioUseCase(repository);
    }

    @Bean
    public EditarUsuarioUseCase editarUsuarioUseCase(
            UsuarioMapper mapper,
            UsuarioRepository repository
    ) {
        return new EditarUsuarioUseCase(mapper, repository);
    }

    @Bean
    public ListarUsuarioUseCase listarUsuarioUseCase(
            UsuarioMapper mapper,
            UsuarioRepository repository
    ) {
        return new ListarUsuarioUseCase(mapper, repository);
    }

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) {
        return new RefreshTokenUseCase(tokenService, usuarioRepository);
    }

    @Bean
    public LoginUsuarioUseCase loginUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        return new LoginUsuarioUseCase(
                usuarioRepository,
                passwordEncoder,
                tokenService
        );
    }


    @Bean
    public LogoutUseCase logoutUseCase(
            CookieService cookieService,
            TokenService tokenService
    ) {
        return new LogoutUseCase(cookieService, tokenService);
    }

}

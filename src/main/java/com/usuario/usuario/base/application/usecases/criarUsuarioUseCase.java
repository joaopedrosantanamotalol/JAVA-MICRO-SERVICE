package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;
import com.usuario.usuario.base.persistence.repository.UsuarioJPARepository;

public class criarUsuarioUseCase {
    
    private final UsuarioMapper mapper;
    private final UsuarioJPARepository repository;

    public criarUsuarioUseCase(UsuarioMapper mapper, UsuarioJPARepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

        

}

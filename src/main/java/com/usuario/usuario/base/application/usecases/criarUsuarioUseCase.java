package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;
import com.usuario.usuario.base.persistence.repository.UsuarioJPARepository;

public class criarUsuarioUseCase {
    
    private final UsuarioMapper mapper;
    private final UsuarioRepository repository;

    

    public criarUsuarioUseCase(UsuarioMapper mapper, UsuarioRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }



    public UsuarioResponse executar(UsuarioRequest request){
        UsuarioEntity entity = repository.salvar(request);
    }

}

package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;

public class CriarUsuarioUseCase {
    
    private final UsuarioMapper mapper;
    private final UsuarioRepository repository;

    

    public CriarUsuarioUseCase(UsuarioMapper mapper, UsuarioRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }



    public UsuarioResponse executar(UsuarioRequest request){
        
        UsuarioEntity entity = mapper.toDomain(request);

        UsuarioEntity salvo = repository.salvar(entity);

        return mapper.toResponse(salvo);

    }

}

package com.comercio.comercio.base.application.usecases;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.entites.ComercioEntity;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
import com.comercio.comercio.base.persistence.mapper.ComercioMapper;

public class CriarComercioUseCase {
    
    private final ComercioDOMAINRepository repository;
    private final ComercioMapper mapper;
    public CriarComercioUseCase(ComercioDOMAINRepository repository, ComercioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ComercioResponse executar(ComercioRequest request){

        ComercioEntity entity = mapper.toDomain(request);

        ComercioEntity salvo = repository.salvar(entity);

        return mapper.toResponse(salvo);

    }

}

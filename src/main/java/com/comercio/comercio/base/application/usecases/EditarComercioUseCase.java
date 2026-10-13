package com.comercio.comercio.base.application.usecases;

import org.springframework.stereotype.Service;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.entites.ComercioEntity;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
import com.comercio.comercio.base.persistence.mapper.ComercioMapper;

@Service
public class EditarComercioUseCase {

    private final ComercioDOMAINRepository repository;
    private final ComercioMapper mapper;

    public EditarComercioUseCase(ComercioDOMAINRepository repository, ComercioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public ComercioResponse executar(Long id, ComercioRequest request) {

        ComercioEntity entity = mapper.toDomain(request);

        ComercioEntity salvo = repository.editar(id, entity);

        return mapper.toResponse(salvo);
    }


}

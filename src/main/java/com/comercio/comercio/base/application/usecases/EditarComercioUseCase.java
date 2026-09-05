package com.comercio.comercio.base.application.usecases;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.entites.ComercioEntity;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
import com.comercio.comercio.base.persistence.mapper.ComercioMapper;

public class EditarComercioUseCase {

    private final ComercioDOMAINRepository repository;
    private final ComercioMapper mapper;

    public EditarComercioUseCase(ComercioDOMAINRepository repository, ComercioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ComercioResponse executar(Long id, ComercioRequest request){
        ComercioEntity entity = repository.acharPorId(id)
        .orElseThrow(() -> new RuntimeException("nao foi possivel achar"));

        mapper.atualizarDomain(request, entity);

        ComercioEntity salvo = repository.salvar(entity);

        return mapper.toResponse(salvo);

    }

}

package com.comercio.comercio.base.application.usecases;

import java.util.List;

import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
import com.comercio.comercio.base.persistence.mapper.ComercioMapper;

public class ListarComercioUseCase {
    
    private final ComercioDOMAINRepository repository;
    private final ComercioMapper mapper;

    public ListarComercioUseCase(ComercioDOMAINRepository repository, ComercioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ComercioResponse> executar(){
        return repository.listar()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

}

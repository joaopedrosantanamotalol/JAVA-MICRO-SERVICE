package com.comercio.comercio.base.application.usecases;

import org.springframework.stereotype.Service;

import java.util.List;

import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
import com.comercio.comercio.base.persistence.mapper.ComercioMapper;

@Service
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

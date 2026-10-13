package com.comercio.comercio.base.application.usecases;

import org.springframework.stereotype.Service;

import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;

@Service
public class ExcluirComercioUseCase {
    
    private final ComercioDOMAINRepository repository;

    public ExcluirComercioUseCase(ComercioDOMAINRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id){

        repository.deletar(id);

    }

}

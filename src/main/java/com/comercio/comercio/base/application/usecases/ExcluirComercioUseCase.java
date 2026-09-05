package com.comercio.comercio.base.application.usecases;

import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;

public class ExcluirComercioUseCase {
    
    private final ComercioDOMAINRepository repository;

    public ExcluirComercioUseCase(ComercioDOMAINRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id){

        repository.deletar(id);

    }

}

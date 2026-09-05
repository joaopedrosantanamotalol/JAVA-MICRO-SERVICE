package com.comercio.comercio.base.persistence.repositories;

import java.util.List;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;

public class ComercioRepositoryImpl implements ComercioDOMAINRepository {
    
    private final ComercioJPARepository repository;


    public ComercioRepositoryImpl(ComercioJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public ComercioResponse criarComercio(ComercioRequest request){
        
    }

    @Override
    public ComercioResponse editarComercio(ComercioRequest request, Long id){

    }

    @Override
    public List<ComercioResponse> listarComercios(){

    }

    @Override
    public void deletarComercio(){

    }

}

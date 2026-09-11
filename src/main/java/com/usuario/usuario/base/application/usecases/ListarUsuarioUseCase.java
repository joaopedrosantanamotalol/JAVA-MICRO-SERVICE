package com.usuario.usuario.base.application.usecases;

import java.util.List;

import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;

public class ListarUsuarioUseCase {

    private final UsuarioMapper mapper;
    private final UsuarioRepository repository;

    public ListarUsuarioUseCase(UsuarioMapper mapper, UsuarioRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<UsuarioResponse> executar(){
        return repository.listarTodos()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

}

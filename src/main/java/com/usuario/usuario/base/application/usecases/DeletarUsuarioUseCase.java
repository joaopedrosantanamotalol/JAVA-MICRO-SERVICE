package com.usuario.usuario.base.application.usecases;

import com.usuario.usuario.base.domain.repository.UsuarioRepository;

public class DeletarUsuarioUseCase {

    private final UsuarioRepository repository;

    public DeletarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id){

        repository.excluir(id);

    }

}

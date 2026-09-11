package com.usuario.usuario.base.domain.repository;

import java.util.List;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface UsuarioRepository {

    UsuarioEntity salvar(UsuarioEntity entity);

    List<UsuarioEntity> listarTodos();

    UsuarioEntity editar(Long id, UsuarioEntity entity);

    void excluir(Long id);

}

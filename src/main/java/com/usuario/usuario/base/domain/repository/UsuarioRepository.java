package com.usuario.usuario.base.domain.repository;

import java.util.List;
import java.util.Optional;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface UsuarioRepository {

    UsuarioEntity salvar(UsuarioEntity entity);

    List<UsuarioEntity> listarTodos();

    UsuarioEntity editar(Long id, UsuarioEntity entity);

    Optional<UsuarioEntity> acharPorId(Long id);

    void excluir(Long id);

}

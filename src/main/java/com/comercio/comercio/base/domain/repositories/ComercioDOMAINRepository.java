package com.comercio.comercio.base.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.comercio.comercio.base.domain.entites.ComercioEntity;

public interface ComercioDOMAINRepository {

    ComercioEntity salvar(ComercioEntity comercio);

    ComercioEntity editar(Long id, ComercioEntity comercio);

    Optional<ComercioEntity> acharPorId(Long id);

    List<ComercioEntity> listar();

    void deletar(Long id);

    boolean existePorId(Long id);

}

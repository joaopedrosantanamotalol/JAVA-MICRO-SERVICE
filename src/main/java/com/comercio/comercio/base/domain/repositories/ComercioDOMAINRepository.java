package com.comercio.comercio.base.domain.repositories;

import java.util.List;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;

public interface ComercioDOMAINRepository {

    ComercioResponse criarComercio(ComercioRequest request);

    ComercioResponse editarComercio(ComercioRequest request, Long id);

    List<ComercioResponse> listarComercios();

    void deletarComercio();

}


package com.comercio.comercio.base.persistence.mapper;

import org.mapstruct.Mapper;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.entites.ComercioEntity;
import com.comercio.comercio.base.persistence.entites.ComercioPersistence;


@Mapper(componentModel = "spring")
public interface ComercioMapper {

    // Request -> Domain
    ComercioEntity toDomain(ComercioRequest request);

    // Domain -> Persistence
    ComercioPersistence toPersistence(ComercioEntity entity);

    // Persistence -> Domain
    ComercioEntity toDomain(ComercioPersistence persistence);

    // Domain -> Response
    ComercioResponse toResponse(ComercioEntity entity);
}


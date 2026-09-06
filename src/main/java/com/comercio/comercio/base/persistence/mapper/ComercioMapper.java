package com.comercio.comercio.base.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.domain.entites.ComercioEntity;
import com.comercio.comercio.base.persistence.entites.ComercioPersistence;

@Mapper(componentModel = "spring")
public interface ComercioMapper {

    @Mapping(target = "id", ignore = true)
    ComercioEntity toDomain(ComercioRequest request);

    ComercioPersistence toPersistence(ComercioEntity entity);

    ComercioEntity toDomain(ComercioPersistence persistence);

    ComercioResponse toResponse(ComercioEntity entity);

    @Mapping(target = "id", ignore = true)
    void atualizarDomain(
        ComercioRequest request,
        @MappingTarget ComercioEntity entity
    );
}
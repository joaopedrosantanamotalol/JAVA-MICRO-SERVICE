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

    /*Converte para ComercioEntidade recendo ComercioRequest */
    @Mapping(target = "id", ignore = true)
    ComercioEntity toDomain(ComercioRequest request);

    /* Converte para FrameworkEntity recebendo Entidade de Dominio */
    ComercioPersistence toPersistence(ComercioEntity entity);

    /* Converte para Entidade de dominio recebendo de Framework */
    ComercioEntity toDomain(ComercioPersistence persistence);

    /* Converte para responseDTO recebendo entidade de Dominio */
    ComercioResponse toResponse(ComercioEntity entity);

    @Mapping(target = "id", ignore = true)
    void atualizarDomain(
        ComercioRequest request,
        @MappingTarget ComercioEntity entity
    );
}
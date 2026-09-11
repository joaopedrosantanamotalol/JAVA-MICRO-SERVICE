package com.usuario.usuario.base.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.persistence.entities.UsuarioPersistence;

@Mapper (componentModel = "spring")
public interface UsuarioMapper {

    UsuarioPersistence toPersistence(UsuarioEntity entity);

    UsuarioEntity toDomain(UsuarioPersistence entity);

    @Mapping (target = "id", ignore = true)
    UsuarioEntity toDomain(UsuarioRequest entity);

    UsuarioResponse toResponse(UsuarioEntity entity);
}

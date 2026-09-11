package com.usuario.usuario.base.persistence.mapper;

import org.mapstruct.Mapper;

import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.persistence.entities.UsuarioPersistence;

@Mapper (componentModel = "spring")
public interface UsuarioMapper {

    UsuarioPersistence toPersistence(UsuarioEntity entity);

    UsuarioEntity toDomain(UsuarioPersistence entity);

    UsuarioEntity toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(UsuarioEntity entity);
}

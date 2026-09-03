package com.example.produto_branch.base.infra.persistence.mapper;

import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.infra.persistence.entities.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoPersistenceMapper {

    ProdutoEntity toEntity(ProdutoDomain produto);

    ProdutoDomain toDomain(ProdutoEntity entity);
}
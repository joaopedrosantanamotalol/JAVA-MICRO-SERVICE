package com.example.produto_branch.base.application.dto;

import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoDtoMapper {

    @Mapping(target = "id", ignore = true)
    ProdutoDomain toDomain(ProdutoRequest request);

    ProdutoResponse toResponse(ProdutoDomain produto);
}

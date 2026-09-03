package com.example.produto_branch.base.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.example.produto_branch.base.domain.entities.ProdutoDomain;

public interface ProdutoRepository {
    
    ProdutoDomain salvar(ProdutoDomain produto);

    ProdutoDomain editar(Long id, ProdutoDomain entity);

    Optional<ProdutoDomain> acharPorID(Long id);

    void Deletar(Long id);

    List<ProdutoDomain> listarProdutos();

    Boolean existePorId(Long id);

}

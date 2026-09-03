package com.example.produto_branch.base.application.usecases;

import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;
import com.example.produto_branch.base.domain.services.ProdutoService;

public class DeletarProdutoUseCase {
   
    private final ProdutoService service;

    private final ProdutoRepository repository;

    public DeletarProdutoUseCase(ProdutoService service, ProdutoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public void DeletarProduto(Long id){

        ProdutoDomain produto = repository.acharPorID(id)
        .orElseThrow(() -> new RuntimeException("não foi achado o produto"));

        service.ValidarQuantidade(produto);

        repository.Deletar(id);

    }

}

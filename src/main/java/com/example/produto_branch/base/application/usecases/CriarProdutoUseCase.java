package com.example.produto_branch.base.application.usecases;

import com.example.produto_branch.base.application.dto.ProdutoDtoMapper;
import com.example.produto_branch.base.application.dto.ProdutoRequest;
import com.example.produto_branch.base.application.dto.ProdutoResponse;
import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;
import com.example.produto_branch.base.domain.services.ProdutoService;

public class CriarProdutoUseCase {
    
    private final ProdutoService service;

    private final ProdutoDtoMapper mapper;

    private final ProdutoRepository repository;

    public CriarProdutoUseCase(ProdutoService service, ProdutoDtoMapper mapper, ProdutoRepository repository) {
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    public ProdutoResponse executar(ProdutoRequest request){

    ProdutoDomain produto = mapper.toDomain(request);

    service.ValidarQuantidade(produto);

    ProdutoDomain salvo = repository.salvar(produto);

    return mapper.toResponse(salvo);

    }

}

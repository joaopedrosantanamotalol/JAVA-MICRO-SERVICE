package com.example.produto_branch.base.application.usecases;

import org.springframework.stereotype.Service;

import com.example.produto_branch.base.application.dto.ProdutoDtoMapper;
import com.example.produto_branch.base.application.dto.ProdutoRequest;
import com.example.produto_branch.base.application.dto.ProdutoResponse;
import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;
import com.example.produto_branch.base.domain.services.ProdutoService;

@Service
public class EditarProdutoUseCase {
 
    private final ProdutoService service;

    private final ProdutoDtoMapper mapper;

    private final ProdutoRepository repository;

    public EditarProdutoUseCase(ProdutoService service, ProdutoDtoMapper mapper, ProdutoRepository repository) {
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    public ProdutoResponse executar(Long id, ProdutoRequest request){
        ProdutoDomain produto = mapper.toDomain(request);

        if(!repository.existePorId(id)){
            throw new RuntimeException("produto não achado");
        }

        service.ValidarQuantidade(produto);

        ProdutoDomain atualizado = repository.editar(id, produto);

        return mapper.toResponse(atualizado);

    }

}

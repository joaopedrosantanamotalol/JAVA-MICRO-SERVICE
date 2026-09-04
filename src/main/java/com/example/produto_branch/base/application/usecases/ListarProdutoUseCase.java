package com.example.produto_branch.base.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.produto_branch.base.application.dto.ProdutoDtoMapper;
import com.example.produto_branch.base.application.dto.ProdutoResponse;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;

@Service
public class ListarProdutoUseCase {

    private final ProdutoDtoMapper mapper;

    private final ProdutoRepository repository;

    public ListarProdutoUseCase(ProdutoDtoMapper mapper, ProdutoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<ProdutoResponse> executar(){
        return repository.listarProdutos()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

}

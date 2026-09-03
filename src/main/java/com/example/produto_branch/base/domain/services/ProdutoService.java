package com.example.produto_branch.base.domain.services;
import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;

public class ProdutoService {
    
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // esse método verifica a quantidade de produtos presentes
    public void ValidarQuantidade(ProdutoDomain produto){
        if(produto.getQuantidade() < 0){
            throw new IllegalArgumentException("Não podemos ter produtos negativos");
        }
    }

    // esse método verifica se o produto existe, se não lança uma exceção de inexistencia
    public void ValidarExistencia(ProdutoDomain produto){
        if(!repository.existePorId(produto.getId())){
            throw new RuntimeException("esse produto não existe");
        }
    }

    

}

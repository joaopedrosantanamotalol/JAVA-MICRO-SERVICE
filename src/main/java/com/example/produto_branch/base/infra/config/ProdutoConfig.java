package com.example.produto_branch.base.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.produto_branch.base.domain.repositories.ProdutoRepository;
import com.example.produto_branch.base.domain.services.ProdutoService;

@Configuration
public class ProdutoConfig {

    @Bean
    public ProdutoService produtoService(ProdutoRepository repository) {
        return new ProdutoService(repository);
    }
}

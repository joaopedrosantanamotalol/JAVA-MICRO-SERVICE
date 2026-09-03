package com.example.produto_branch.base.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produto_branch.base.infra.persistence.entities.ProdutoEntity;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long> {
    
}

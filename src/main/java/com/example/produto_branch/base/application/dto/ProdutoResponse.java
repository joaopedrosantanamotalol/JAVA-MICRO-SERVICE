package com.example.produto_branch.base.application.dto;

import java.util.List;

import com.example.produto_branch.base.domain.enums.produto.Categoria;

public record ProdutoResponse(

    Long id,
    String nome,
    int quantidade,
    String descricao,
    double Valor_Unitario,
    List<Categoria> categoria

) {}

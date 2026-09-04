package com.example.produto_branch.base.presentation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produto_branch.base.application.dto.ProdutoRequest;
import com.example.produto_branch.base.application.dto.ProdutoResponse;
import com.example.produto_branch.base.application.usecases.CriarProdutoUseCase;
import com.example.produto_branch.base.application.usecases.DeletarProdutoUseCase;
import com.example.produto_branch.base.application.usecases.EditarProdutoUseCase;
import com.example.produto_branch.base.application.usecases.ListarProdutoUseCase;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final CriarProdutoUseCase criarcase;

    private final EditarProdutoUseCase editarcase;

    private final ListarProdutoUseCase listarcase;

    private final DeletarProdutoUseCase deletarcase;

    public ProdutoController(CriarProdutoUseCase criarcase, EditarProdutoUseCase editarcase,
            ListarProdutoUseCase listarcase, DeletarProdutoUseCase deletarcase) {
        this.criarcase = criarcase;
        this.editarcase = editarcase;
        this.listarcase = listarcase;
        this.deletarcase = deletarcase;
    }

    @GetMapping("/todos")
    public List<ProdutoResponse> listarProduto(){
        return listarcase.executar();
    }

    @PostMapping()
    public ProdutoResponse criarProduto(@RequestBody ProdutoRequest request){
        return criarcase.executar(request);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizarProduto(@RequestBody ProdutoRequest request, @PathVariable Long id){
        return editarcase.executar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id){
        deletarcase.DeletarProduto(id);
    }

}

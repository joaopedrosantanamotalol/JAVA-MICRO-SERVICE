package com.comercio.comercio.base.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.comercio.base.application.dto.ComercioRequest;
import com.comercio.comercio.base.application.dto.ComercioResponse;
import com.comercio.comercio.base.application.usecases.CriarComercioUseCase;
import com.comercio.comercio.base.application.usecases.EditarComercioUseCase;
import com.comercio.comercio.base.application.usecases.ExcluirComercioUseCase;
import com.comercio.comercio.base.application.usecases.ListarComercioUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController 
@RequestMapping("/comercio")
public class ComercioController {
    
    private final CriarComercioUseCase criar;
    private final EditarComercioUseCase editar;
    private final ListarComercioUseCase listar;
    private final ExcluirComercioUseCase excluir;

    public ComercioController(CriarComercioUseCase criar, EditarComercioUseCase editar, ListarComercioUseCase listar,
            ExcluirComercioUseCase excluir) {
        this.criar = criar;
        this.editar = editar;
        this.listar = listar;
        this.excluir = excluir;
    }

    @GetMapping()
    public List<ComercioResponse> listarComercio(){
        return listar.executar();
    }

    @PostMapping()
    public ComercioResponse criarComercio(@RequestBody ComercioRequest request){
        return criar.executar(request);
    }

    @PutMapping("/{id}")
    public ComercioResponse editarComercio(@PathVariable Long id, @RequestBody ComercioRequest request){
        return editar.executar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletarComercio(@PathVariable Long id){
        excluir.executar(id);
    }

}

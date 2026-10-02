package com.usuario.usuario.base.presentation.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.usuario.base.application.dto.UsuarioRequest;
import com.usuario.usuario.base.application.dto.UsuarioResponse;
import com.usuario.usuario.base.application.usecases.CriarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.DeletarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.EditarUsuarioUseCase;
import com.usuario.usuario.base.application.usecases.ListarUsuarioUseCase;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
 
    private final CriarUsuarioUseCase criar;
    private final DeletarUsuarioUseCase deletar;
    private final EditarUsuarioUseCase editar;
    private final ListarUsuarioUseCase listar;

    public UsuarioController(CriarUsuarioUseCase criar, DeletarUsuarioUseCase deletar, EditarUsuarioUseCase editar,
            ListarUsuarioUseCase listar) {
        this.criar = criar;
        this.deletar = deletar;
        this.editar = editar;
        this.listar = listar;
    }

    @GetMapping()
    public List<UsuarioResponse> listarUsuario(){
        return listar.executar();
    }

    @PostMapping()
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest request){
        return criar.executar(request);
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioRequest request,
            Authentication authentication
    ) {
        String emailLogado = authentication.getName();

        return editar.executar(
                request,
                id,
                emailLogado
        );
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id){
        deletar.executar(id);
    }

}

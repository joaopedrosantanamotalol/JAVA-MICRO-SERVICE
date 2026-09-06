package com.usuario.usuario.base.application.dto;

import com.usuario.usuario.base.domain.enums.Sexo;

public record UsuarioResponse(

 Long id,
 String nome,
 String email,
 Sexo sexo,
 String telefone,
 String cpf

) {}

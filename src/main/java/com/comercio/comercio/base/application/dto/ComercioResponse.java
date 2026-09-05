package com.comercio.comercio.base.application.dto;

import java.util.List;

import com.comercio.comercio.base.domain.enums.CategoriaComercio;
import com.comercio.comercio.base.domain.enums.TamanhoComercio;

public record ComercioResponse(
    
 Long id,
 
 String nome,

 String descricao,

 String cnpj,

 List<CategoriaComercio> comercio,

 TamanhoComercio tamanho

) {}

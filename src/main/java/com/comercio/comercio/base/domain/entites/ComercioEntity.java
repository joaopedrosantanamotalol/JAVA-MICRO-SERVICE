package com.comercio.comercio.base.domain.entites;

import java.util.List;

import com.comercio.comercio.base.domain.enums.CategoriaComercio;
import com.comercio.comercio.base.domain.enums.TamanhoComercio;

public class ComercioEntity {

    private Long id;

    private String nome;

    private String descricao;

    private String cnpj;

    private List<CategoriaComercio> comercio;

    private TamanhoComercio tamanho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<CategoriaComercio> getComercio() {
        return comercio;
    }

    public void setComercio(List<CategoriaComercio> comercio) {
        this.comercio = comercio;
    }

    public TamanhoComercio getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoComercio tamanho) {
        this.tamanho = tamanho;
    }

    

}

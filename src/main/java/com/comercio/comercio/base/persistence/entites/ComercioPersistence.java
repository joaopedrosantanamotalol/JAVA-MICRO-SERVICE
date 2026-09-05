package com.comercio.comercio.base.persistence.entites;

import java.util.List;

import com.comercio.comercio.base.persistence.enums.CategoriaComercio;
import com.comercio.comercio.base.persistence.enums.TamanhoComercio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "comercio")
public class ComercioPersistence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<CategoriaComercio> comercio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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


package com.comercio.comercio.base.persistence.entites;

import java.util.ArrayList;
import java.util.List;

import com.comercio.comercio.base.domain.enums.CategoriaComercio;
import com.comercio.comercio.base.domain.enums.TamanhoComercio;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "comercio")
public class ComercioPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @ElementCollection
    @CollectionTable(
        name = "comercio_categorias",
        joinColumns = @JoinColumn(name = "comercio_id")
    )
    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<CategoriaComercio> categorias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    public List<CategoriaComercio> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaComercio> categorias) {
        this.categorias = categorias;
    }

    public TamanhoComercio getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoComercio tamanho) {
        this.tamanho = tamanho;
    }
}

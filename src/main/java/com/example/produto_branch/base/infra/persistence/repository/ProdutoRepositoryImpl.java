package com.example.produto_branch.base.infra.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.example.produto_branch.base.domain.entities.ProdutoDomain;
import com.example.produto_branch.base.domain.repositories.ProdutoRepository;
import com.example.produto_branch.base.infra.persistence.entities.ProdutoEntity;
import com.example.produto_branch.base.infra.persistence.mapper.ProdutoPersistenceMapper;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    
    private final ProdutoPersistenceMapper persistence_mapper;
    private final ProdutoJpaRepository repository;    

    public ProdutoRepositoryImpl(ProdutoPersistenceMapper persistence_mapper, ProdutoJpaRepository repository) {
        this.persistence_mapper = persistence_mapper;
        this.repository = repository;
    }

    @Override
    public ProdutoDomain salvar(ProdutoDomain domain){
        ProdutoEntity entity = persistence_mapper.toEntity(domain);

        ProdutoEntity salvo = repository.save(entity);

        return persistence_mapper.toDomain(salvo);

    }

    @Override
    public Optional<ProdutoDomain> acharPorID(Long id){
       
        return repository.findById(id)
        .map(persistence_mapper::toDomain);
    } 

    @Override
    public ProdutoDomain editar(Long id, ProdutoDomain dados){

        ProdutoEntity entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("produto não achado"));

        entity.setCategoria(dados.getCategoria());
        entity.setDescricao(dados.getDescricao());
        entity.setNome(dados.getNome());
        entity.setQuantidade(dados.getQuantidade());
        entity.setValorUnitario(dados.getValorUnitario());

        ProdutoEntity atualizado = repository.save(entity);

        return persistence_mapper.toDomain(atualizado);

    }

    @Override
    public void Deletar(Long id){
        repository.deleteById(id);
    }

    @Override
    public List<ProdutoDomain> listarProdutos(){
        return repository.findAll()
        .stream()
        .map(persistence_mapper::toDomain)
        .toList();
    }

    @Override
    public Boolean existePorId(Long id){
        return repository.existsById(id);
    }

}

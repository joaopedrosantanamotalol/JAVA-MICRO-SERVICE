package com.usuario.usuario.base.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;
import com.usuario.usuario.base.domain.repository.UsuarioRepository;
import com.usuario.usuario.base.persistence.entities.UsuarioPersistence;
import com.usuario.usuario.base.persistence.mapper.UsuarioMapper;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    
    private final UsuarioMapper mapper;
    private final UsuarioJPARepository jpaRep;


    public UsuarioRepositoryImpl(UsuarioMapper mapper, UsuarioJPARepository jpaRep) {
        this.mapper = mapper;
        this.jpaRep = jpaRep;
    }

    @Override
    public UsuarioEntity salvar(UsuarioEntity entity){

        UsuarioPersistence persistence = mapper.toPersistence(entity);

        UsuarioPersistence salvo = jpaRep.save(persistence);

        return mapper.toDomain(salvo);

    }

    @Override
    public List<UsuarioEntity> listarTodos(){
        return jpaRep.findAll()
        .stream()
        .map(mapper::toDomain)
        .toList();
    }

    @Override
    public UsuarioEntity editar(Long id, UsuarioEntity entity){

        UsuarioPersistence entidade = jpaRep.findById(id)
        .orElseThrow(() -> new RuntimeException("deu pra char não"));

        entidade.setCpf(entity.getCpf());
        entidade.setEmail(entity.getEmail());
        entidade.setNome(entity.getNome());
        entidade.setSexo(entity.getSexo());
        entidade.setSenha(entity.getSenha());
        entidade.setTelefone(entity.getTelefone());

        UsuarioPersistence atualizado = jpaRep.save(entidade);

        return mapper.toDomain(atualizado);

    }

    @Override
    public Optional<UsuarioEntity> acharPorId(Long id){
        return jpaRep.findById(id)
        .map(mapper::toDomain);
    }

    @Override
    public void excluir(Long id){

        if(!jpaRep.existsById(id)){
            throw new RuntimeException("não existe usuario com esse id beleza");
        }

        jpaRep.deleteById(id);

    }

    @Override
    public Boolean existePorId(Long id){
        /* usuario não existe */
        if(!jpaRep.existsById(id)){
            throw new RuntimeException("esse usuario não existe");
        }
        return true;
    }

    @Override 
    public Optional<UsuarioEntity> acharPorEmail(String email){
        return jpaRep.findByEmail(email);
    }

}

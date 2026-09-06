package com.usuario.usuario.base.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.usuario.base.domain.entities.UsuarioEntity;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity,Long> {
    
}

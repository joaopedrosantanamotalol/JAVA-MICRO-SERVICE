package com.usuario.usuario.base.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.usuario.base.persistence.entities.UsuarioPersistence;

public interface UsuarioJPARepository extends JpaRepository<UsuarioPersistence,Long> {
    Optional<UsuarioPersistence> findByEmail(String email);
}

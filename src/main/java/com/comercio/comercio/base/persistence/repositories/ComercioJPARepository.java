package com.comercio.comercio.base.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comercio.comercio.base.persistence.entites.ComercioPersistence;

public interface ComercioJPARepository extends JpaRepository<ComercioPersistence,Long> {
    
}

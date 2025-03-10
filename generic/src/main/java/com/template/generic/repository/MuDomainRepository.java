package com.template.generic.repository;

import com.template.generic.model.MuDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * -------------------------------------------------------------------------*
 * Información General
 * -------------------------------------------------------------------------*
 * Código de Aplicación:
 * Código de Objeto:
 * Descripción:
 * Author Prog.: Crisvel Camacho
 * -------------------------------------------------------------------------*
 * Fecha | Author | Comentario
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuDomainRepository extends JpaRepository<MuDomain, String> {

    @Query( "SELECT d " +
            "FROM MuDomain d " +
            "WHERE d.deleted = FALSE " +
            "AND d.id = :domainId ")
    Optional<MuDomain> findById(@Param("domainId") String domainId);
    List<MuDomain> findAllByDeletedFalse();
}

package com.template.generic.repository;

import com.template.generic.model.RcClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Repository
public interface RcClientRepository extends JpaRepository<RcClient, Long> {
    @Query("SELECT new com.template.generic.model.dto.RcClientDto(c) " +
           "FROM RcClient c " +
           "WHERE c.deleted = FALSE " +
           "  AND (:filter IS NULL OR UPPER(c.names) LIKE :filter " +
           "   OR UPPER(c.dni) LIKE :filter " +
           "   OR UPPER(c.lastnames) LIKE :filter " +
           "   OR UPPER(c.phone) LIKE :filter " +
           "   OR UPPER(c.category) LIKE :filter )")
    <T> Page<T> pageWithFilter(@Param("filter") String filter,
                           Pageable pageable, Class<T> type);
    Optional<RcClient> findByDni(String dni);
}

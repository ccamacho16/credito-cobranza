package com.template.generic.repository;

import com.template.generic.model.RcClient;
import com.template.generic.model.RcGuarantor;
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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Repository
public interface RcGuarantorRepository extends JpaRepository<RcGuarantor, Long> {
    @Query("SELECT new com.template.generic.model.dto.RcGuarantorDto(g) " +
            "FROM RcGuarantor g " +
            "WHERE g.deleted = FALSE " +
            "  AND (:filter IS NULL OR UPPER(g.names) LIKE :filter " +
            "   OR UPPER(g.dni) LIKE :filter " +
            "   OR UPPER(g.lastnames) LIKE :filter " +
            "   OR UPPER(g.phone) LIKE :filter )")
    <T> Page<T> pageWithFilter(@Param("filter") String filter,
                               Pageable pageable, Class<T> type);
    Optional<RcGuarantor> findByDni(String dni);
}

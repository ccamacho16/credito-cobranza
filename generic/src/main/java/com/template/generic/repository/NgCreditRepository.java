package com.template.generic.repository;

import com.template.generic.model.NgCredit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
 * 11.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Repository
public interface NgCreditRepository extends JpaRepository<NgCredit, Long> {

    @Query("SELECT new com.template.generic.model.dto.select.CreditCabDto(c) " +
            "FROM NgCredit c JOIN c.client cl JOIN c.creditOfficer co " +
            "WHERE c.deleted = FALSE " +
            "  AND (:filter IS NULL " +
            "   OR UPPER(CAST(c.id AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(CAST(c.generationDate AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(CAST(c.frequencyPayment AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(CAST(c.term AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(CAST(c.type AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(CAST(c.status AS string)) LIKE UPPER(:filter) " +
            "   OR UPPER(cl.names) LIKE UPPER(:filter) " +
            "   OR UPPER(cl.lastnames) LIKE UPPER(:filter) " +
            "   OR UPPER(co.names) LIKE UPPER(:filter) " +
            "   OR UPPER(co.lastnames) LIKE UPPER(:filter) )")
    <T> Page<T> pageWithFilter(@Param("filter") String filter,
                               Pageable pageable, Class<T> type);
}

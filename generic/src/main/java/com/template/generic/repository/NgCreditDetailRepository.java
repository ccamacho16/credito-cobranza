package com.template.generic.repository;

import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.dto.NgCreditDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

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
public interface NgCreditDetailRepository extends JpaRepository<NgCreditDetail, Long> {

    @Query("SELECT new com.template.generic.model.dto.NgCreditDetailDto(cd) " +
           "FROM NgCreditDetail cd " +
           "WHERE cd.deleted = FALSE " +
           "  AND cd.credit.id = :idCredit " +
           "ORDER BY cd.shareNumber ASC")
    Set<NgCreditDetailDto> listFromCredit(@Param("idCredit") Long idCredit);
}

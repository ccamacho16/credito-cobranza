package com.template.generic.repository;

import com.template.generic.model.NgCreditGuarantor;
import com.template.generic.model.dto.RcGuarantorDto;
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
public interface NgCreditGuarantorRepository extends JpaRepository<NgCreditGuarantor, Long> {
    @Query("SELECT new com.template.generic.model.dto.RcGuarantorDto(cg.guarantor) " +
           "FROM NgCreditGuarantor cg " +
           "WHERE cg.deleted = FALSE " +
           "  AND cg.credit.id = :idCredit")
    Set<RcGuarantorDto> listGuarantorByCredit(@Param("idCredit") Long idCredit);
}

package com.template.generic.repository;

import com.template.generic.model.NgCredit;
import org.springframework.data.jpa.repository.JpaRepository;
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
}

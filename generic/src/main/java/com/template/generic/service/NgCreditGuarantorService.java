package com.template.generic.service;

import com.template.generic.model.dto.RcGuarantorDto;

import java.util.List;
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
 * 13.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface NgCreditGuarantorService {
    void createFromList(Set<Long> list, Long idCredit) ;
 }

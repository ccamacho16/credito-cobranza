package com.template.generic.service;

import com.template.generic.model.dto.NgCreditDetailDto;
import org.springframework.stereotype.Service;

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
public interface NgCreditDetailService {
    void createFromList(Set<NgCreditDetailDto> list, Long idCredit);
}

package com.template.generic.service;

import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcCreditOfficerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface RcCreditOfficerService {
    Page<RcCreditOfficerDto> listByFilter(String filter, Pageable pageable);
    RcCreditOfficerDto findById(Long id);
    RcCreditOfficerDto create(RcCreditOfficerDto rcCreditOfficerDto);
    RcCreditOfficerDto update(RcCreditOfficerDto rcCreditOfficerDto);
    void delete(Long id);
}

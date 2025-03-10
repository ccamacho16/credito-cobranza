package com.template.generic.service;

import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcGuarantorDto;
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
public interface RcGuarantorService {
    Page<RcGuarantorDto> listByFilter(String filter, Pageable pageable);
    RcGuarantorDto findById(Long id);
    RcGuarantorDto create(RcGuarantorDto rcGuarantorDto);
    RcGuarantorDto update(RcGuarantorDto rcGuarantorDto);
    void delete(Long id);
}

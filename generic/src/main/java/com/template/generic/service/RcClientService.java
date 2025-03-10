package com.template.generic.service;

import com.template.generic.model.RcClient;
import com.template.generic.model.dto.RcClientDto;
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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface RcClientService {
    Page<RcClientDto> listByFilter(String filter, Pageable pageable);
    RcClientDto findById(Long id);
    RcClientDto create(RcClientDto rcClientDto);
    RcClientDto update(RcClientDto rcClientDto);
    void delete(Long id);
}

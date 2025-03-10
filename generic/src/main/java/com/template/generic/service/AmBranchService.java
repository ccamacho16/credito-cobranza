package com.template.generic.service;

import com.template.generic.model.dto.AmBranchDto;
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
 * 17.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface AmBranchService {
    Page<AmBranchDto> listByFilter(String filter, Pageable pageable);
    AmBranchDto findById(Long id);
    AmBranchDto create(AmBranchDto amBranchDto);
    AmBranchDto update(AmBranchDto amBranchDto);
    void delete(Long id);
}

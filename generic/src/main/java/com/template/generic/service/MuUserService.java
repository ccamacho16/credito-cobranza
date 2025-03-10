package com.template.generic.service;

import com.template.generic.model.dto.MuUserDto;
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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuUserService {
    Page<MuUserDto> listByFilter(String filter, Pageable pageable);
    MuUserDto findById(Long id);
    MuUserDto create(MuUserDto muUserDto);
    MuUserDto update(MuUserDto muUserDto);
    void delete(Long id);
}

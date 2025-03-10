package com.template.generic.service;

import com.template.generic.model.dto.select.MenuSelectDto;

import java.util.List;

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
 * 19.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuRolService {

    List<MenuSelectDto> getListMenuBasic(Long idRol);
    public void saveListMenusRol(List<Long> idMenus, Long idRol);
}

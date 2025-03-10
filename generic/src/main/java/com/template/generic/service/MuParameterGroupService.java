package com.template.generic.service;

import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.dto.MuParameterGroupDto;

import java.util.List;
import java.util.Optional;

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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuParameterGroupService {
    List<MuParameterGroupDto> getParameterGroupList();
    Optional<MuParameterGroup> findByGrupo(String group);
    public void save(MuParameterGroup group);
}

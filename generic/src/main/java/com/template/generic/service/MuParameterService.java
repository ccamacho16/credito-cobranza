package com.template.generic.service;

import com.template.generic.model.MuParameter;
import com.template.generic.model.dto.MuParameterDto;

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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuParameterService {
    void saveParameter(MuParameter muParameter);
    List<MuParameterDto> listParametersByNameGroup(String nameGroup);
    MuParameterDto listParameterByGroupAndCode(String nameGroup, String code);
}

package com.template.generic.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
 * 06.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public class ApiUtil {
    public static Pageable buildPageableWithSort(int page, int size, String sortBy, Sort.Direction direction) {
        Sort sort;
        if (StringUtil.isEmptyOrNull(sortBy)) sortBy = "id";
        if (direction != null) sort = Sort.by(direction,  sortBy);
        else sort = Sort.by(sortBy);
        return PageRequest.of(page, size, sort);
    }
}

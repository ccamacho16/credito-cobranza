package com.template.generic.model.enums;

import lombok.Getter;
import lombok.ToString;

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
 * 10.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@ToString
public enum CreditShareEnum {
    PENDIENTE("Pendiente"),
    PARCIAL("Parcial"),
    PAGADO("Pagado");

    private final String share;
    CreditShareEnum(String share) {
        this.share = share;
    }

}

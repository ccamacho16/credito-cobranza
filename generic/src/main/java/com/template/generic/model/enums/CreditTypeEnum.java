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
public enum CreditTypeEnum {
    NUEVO("Nuevo"),
    REPROGRAMADO("Reprogramado"),
    REFINANCIADO("Refinanciado");

    private final String type;
    CreditTypeEnum(String type) {
        this.type = type;
    }

}
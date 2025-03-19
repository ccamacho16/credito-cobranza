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
 * 12.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@ToString
public enum FrecuencyPaymentEnum {
    MENSUAL("Mensual"),
    QUINCENAL("Quincenal"),
    SEMANAL("Semanal");

    private final String frecuency;
    FrecuencyPaymentEnum(String frecuency) {
        this.frecuency = frecuency;
    }

}

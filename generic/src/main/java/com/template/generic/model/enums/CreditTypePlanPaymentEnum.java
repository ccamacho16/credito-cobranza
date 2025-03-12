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
 * 11.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@ToString
public enum CreditTypePlanPaymentEnum {
    CUOTAS_FIJAS("Fijas"),
    CUOTAS_DECRECIENTES("Descrecientes");

    private final String typePlanPayment;
    CreditTypePlanPaymentEnum(String typePlanPayment) {
        this.typePlanPayment = typePlanPayment;
    }

}

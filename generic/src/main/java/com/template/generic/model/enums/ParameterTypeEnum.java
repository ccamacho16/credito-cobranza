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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@ToString
public enum ParameterTypeEnum {
    CADENA("CADENA"),
    NUMERICO("NUMERICO"),
    BOOLEANO("BOOLEANO"),
    FECHA("FECHA"),
    LOB("LOB"),
    UNDEFINED("UNDEFINED");
    private final String typeData;
    ParameterTypeEnum(String typeData){
        this.typeData = typeData;
    }
}

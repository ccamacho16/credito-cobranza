package com.template.generic.exception;

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
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public enum ErrorCode {
    E900_INESPERADO("Error inesperado"),
    E910_FALTANTE("Parametro requerido faltante"),
    E920_INVALIDO("Tipo de dato invalido"),
    E930_NOK_ENCONTRADO("Objeto no encontrado"),
    E940_NOK_ESTADO("Estado no permitido"),
    E950_NOK_USUARIO("Usuario no autorizado para la operacion"),
    E960_TIMEOUT("Timeout de la operacion");

    ErrorCode(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    String descripcionError;

    public String getDescripcionError() {
        return descripcionError;
    }
}

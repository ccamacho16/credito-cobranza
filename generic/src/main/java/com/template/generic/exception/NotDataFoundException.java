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
 * 06.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public class NotDataFoundException extends RuntimeException{
    public NotDataFoundException(String message, Throwable cause) {
        super(message,cause);
    }
    public NotDataFoundException(String message) {
        super(message);
    }
}

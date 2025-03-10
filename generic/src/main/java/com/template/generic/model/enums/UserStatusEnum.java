package com.template.generic.model.enums;

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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public enum UserStatusEnum {
    ACTIVO("ACTIVO"),
    BLOQUEADO("BLOQUEADO"),
    SUSPENDIDO("SUSPENDIDO");
    private String estado;
    UserStatusEnum(String estado){
        this.estado = estado;
    }
}

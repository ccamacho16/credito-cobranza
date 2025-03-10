package com.template.generic.util;

import lombok.NoArgsConstructor;

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
 * 24.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@NoArgsConstructor
public class LongUtil {

    public static boolean isNullOrZero(Long valor){
        if(valor==null){
            return true;
        }
        return valor.equals(0L);
    }

    public static boolean isLong(String valor){
        if (valor == null || valor.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(valor);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static Long toLong(String valor){
        try {
            return Long.parseLong(valor);
        }
        catch (Exception e){
            return null;
        }
    }

}
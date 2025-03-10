package com.template.generic.util;

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
public interface Constants {
    interface Params{
        String NIT = "NIT";
        String COUNTRY = "PAIS";
        String COMPANY_NAME = "NOMBRE_EMPRESA";
        String BUSINESS_NAME = "RAZON_SOCIAL";
        String COMPANY_ADDRESS = "DIRECCION";

    }

    interface Domain{
        String GENDER = "GENDER";
        String CATEGORY_CLIENTE = "CATEGORIA_CLIENTE";
        String STATE_CIVIL = "STATE_CIVIL";
        String OCCUPATION = "OCCUPATION";
    }

    interface Message{
        String ERROR_INTERNO = "Ocurrió un error interno";
    }
}

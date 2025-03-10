package com.template.generic.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

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
@AllArgsConstructor
@Getter
public class CustomErrorType implements Serializable {
    public static final String TITULO_LISTA_ARCHIVO_MASIVO = "Archivo";
    public static final String MENSAJE_GENERICO = "Error en proceso de la operación";

    private String title;
    private HttpStatus status;
    private String detail;
    public static ResponseEntity BAD_REQUEST(String title, String detail){
        return new ResponseEntity(new CustomErrorType(title, HttpStatus.BAD_REQUEST,detail),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity SERVER_ERROR(String title, String detail){
        return new ResponseEntity(new CustomErrorType(title, HttpStatus.INTERNAL_SERVER_ERROR,detail),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity NOT_CONTENT(String title, String detail){
        return new ResponseEntity(new CustomErrorType(title, HttpStatus.NO_CONTENT,detail),HttpStatus.NO_CONTENT);
    }
    public static ResponseEntity NOT_FOUND(String title, String detail){
        return new ResponseEntity(new CustomErrorType(title, HttpStatus.NOT_FOUND,detail),HttpStatus.NOT_FOUND);
    }
}

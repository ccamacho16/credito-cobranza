package com.template.generic.model.dto.select;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
 * 18.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDescriptionDto implements Serializable {
    private Long code;
    private String description;
    /*public CodeDescriptionDto(Long code, String description) {
        this.code = code;
        this.description = description;
    }*/

}

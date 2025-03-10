package com.template.generic.model.dto.select;

import com.template.generic.model.MuMenu;
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
 * 19.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSelectDto implements Serializable {
    private Long id;
    private String name;
    private Long idFather;
    private boolean select;

    public MenuSelectDto(MuMenu menu){
        this.id = menu.getId();
        this.name = menu.getName();
        if (menu.getFather() != null)
            this.idFather = menu.getFather().getId();
        this.select = false;
    }
}

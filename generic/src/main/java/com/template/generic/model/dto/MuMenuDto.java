package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.MuMenu;
import com.template.generic.model.MuRoleMenu;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

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
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MuMenuDto extends AbstractAuditableDto {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Integer sequence;
    private MuMenuDto fatherDto;
    private List<MuMenuDto> childrenDto;

    public MuMenuDto(MuMenu menu){
        this.id = menu.getId();
        this.name = menu.getName();
        this.url = menu.getUrl();
        this.icon = menu.getIcon();
        this.sequence = menu.getSequence();
        if (menu.getFather() != null)
            this.fatherDto = MuMenuDto.getDto(menu.getFather());
        else
            this.fatherDto = null;
    }

    public static MuMenuDto getDto(MuMenu menu){
        return MuMenuDto.builder().
        id(menu.getId()).
        name(menu.getName()).
        url(menu.getUrl()).
        icon(menu.getIcon()).
        sequence(menu.getSequence()).build();
    }

    @JsonIgnore
    public MuMenu getEntity(){
        return MuMenu.builder().
        name(this.name).
        url(this.url).
        icon(this.icon).
        sequence(this.sequence).build();
    }

}

package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.MuParameterGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MuParameterGroupDto extends AbstractAuditableDto {
    private Long id;
    private String groupName;
    private String description;

    public MuParameterGroupDto(MuParameterGroup muParameterGroup) {
        this.id = muParameterGroup.getId();
        this.groupName = muParameterGroup.getGroupName();
        this.description = muParameterGroup.getDescription();
    }
    @JsonIgnore
    public static MuParameterGroupDto getDto(MuParameterGroup muParameterGroup) {
        return MuParameterGroupDto.builder().
                id(muParameterGroup.getId()).
                groupName(muParameterGroup.getGroupName()).
                description(muParameterGroup.getDescription()).build();
    }
}

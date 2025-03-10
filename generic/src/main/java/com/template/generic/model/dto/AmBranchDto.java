package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.AmBranch;
import com.template.generic.model.RcClient;
import jakarta.persistence.Column;
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
 * 17.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AmBranchDto extends AbstractAuditableDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;

    public AmBranchDto(AmBranch amBranch){
        this.id = amBranch.getId();
        this.name = amBranch.getName();
        this.description = amBranch.getDescription();
        this.address = amBranch.getAddress();
        this.phone = amBranch.getPhone();
    }

    public static AmBranchDto getDto(AmBranch amBranch){
        return AmBranchDto.builder().
                id(amBranch.getId()).
                name(amBranch.getName()).
                description(amBranch.getDescription()).
                address(amBranch.getAddress()).
                phone(amBranch.getPhone()).build();
    }

    @JsonIgnore
    public AmBranch getEntity(){
        return AmBranch.builder().
                name(this.name).
                description(this.description).
                address(this.address).
                phone(this.phone).build();
    }

}

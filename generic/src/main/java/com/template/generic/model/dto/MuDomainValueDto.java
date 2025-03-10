package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.model.MuDomain;
import com.template.generic.model.MuDomainValue;
import com.template.generic.model.MuMenu;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MuDomainValueDto implements Serializable {
    private Long id;
    private String value;
    private String description;
    private String idDomain;

    public MuDomainValueDto(MuDomainValue domainValue){
        this.id = domainValue.getId();
        this.value = domainValue.getValue();
        this.description = domainValue.getDescription();
        if (domainValue.getDomain() != null)
            this.idDomain = domainValue.getDomain().getId();
    }

    @JsonIgnore
    public static MuDomainValueDto getDto(MuDomainValue domainValue){
        return MuDomainValueDto.builder().
                id(domainValue.getId()).
                value(domainValue.getValue()).
                description(domainValue.getDescription()).
                idDomain(domainValue.getDomain().getId()).build();
    }
}

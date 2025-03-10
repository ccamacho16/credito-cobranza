package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.MuParameter;
import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.enums.ParameterTypeEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

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
public class MuParameterDto extends AbstractAuditableDto {
    private Long id;
    private String code;
    private String description;
    private String stringValue;
    private Boolean boolValue;
    private BigDecimal numberValue;
    private Date dateValue;
    private String lobValue;
    private ParameterTypeEnum parameterType;

    private MuParameterGroupDto muParameterGroupDto;

    public MuParameterDto(MuParameter parameter) {
        this.id = parameter.getId();
        this.code = parameter.getCode();
        this.description = parameter.getDescription();
        this.stringValue = parameter.getStringValue();
        this.boolValue = parameter.getBoolValue();
        this.numberValue = parameter.getNumberValue();
        this.dateValue = parameter.getDateValue();
        this.lobValue = parameter.getLobValue();
        this.parameterType = parameter.getParameterType();
    }

    @JsonIgnore
    public static MuParameterDto getDto(MuParameter parameter) {
        return MuParameterDto.builder().
                id(parameter.getId()).
                code(parameter.getCode()).
                description(parameter.getDescription()).
                stringValue(parameter.getStringValue()).
                boolValue(parameter.getBoolValue()).
                numberValue(parameter.getNumberValue()).
                dateValue(parameter.getDateValue()).
                lobValue(parameter.getLobValue()).
                parameterType(parameter.getParameterType())
                .build();
    }
}

package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.RcClient;
import com.template.generic.model.RcGuarantor;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RcGuarantorDto extends AbstractAuditableDto {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthdate;
    private String civilStatus;
    private String phone;
    private String addressHome;
    private String addressBusiness;
    private String relationship;

    public RcGuarantorDto(RcGuarantor rcGuarantor){
        this.id = rcGuarantor.getId();
        this.dni = rcGuarantor.getDni();
        this.names = rcGuarantor.getNames();
        this.lastnames = rcGuarantor.getLastnames();
        this.birthdate = rcGuarantor.getBirthdate();
        this.civilStatus = rcGuarantor.getCivilStatus();
        this.phone = rcGuarantor.getPhone();
        this.addressHome = rcGuarantor.getAddressHome();
        this.addressBusiness = rcGuarantor.getAddressBusiness();
        this.relationship = rcGuarantor.getRelationship();
    }
    public static RcGuarantorDto getDto(RcGuarantor rcGuarantor){
        return RcGuarantorDto.builder().
                id(rcGuarantor.getId()).
                dni(rcGuarantor.getDni()).
                names(rcGuarantor.getNames()).
                lastnames(rcGuarantor.getLastnames()).
                birthdate(rcGuarantor.getBirthdate()).
                civilStatus(rcGuarantor.getCivilStatus()).
                phone(rcGuarantor.getPhone()).
                addressHome(rcGuarantor.getAddressHome()).
                addressBusiness(rcGuarantor.getAddressBusiness()).
                relationship(rcGuarantor.getRelationship()).build();
    }

    @JsonIgnore
    public RcGuarantor getEntity(){
        return RcGuarantor.builder().
                dni(this.dni).
                names(this.names).
                lastnames(this.lastnames).
                birthdate(this.birthdate).
                civilStatus(this.civilStatus).
                phone(this.phone).
                addressHome(this.addressHome).
                addressBusiness(this.addressBusiness).
                relationship(this.relationship).build();
    }
}

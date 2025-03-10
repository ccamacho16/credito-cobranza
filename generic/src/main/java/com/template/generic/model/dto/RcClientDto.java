package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.MuMenu;
import com.template.generic.model.RcClient;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RcClientDto extends AbstractAuditableDto {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private Date birthdate;
    private String gender;
    private String civilStatus;
    private String phone;
    private String email;
    private Integer incomeMonth;
    private String addressHome;
    private String addressBusiness;
    private String category;
    private String occupation;
    private String comment;

    public RcClientDto(RcClient rcClient){
        this.id = rcClient.getId();
        this.dni = rcClient.getDni();
        this.names = rcClient.getNames();
        this.lastnames = rcClient.getLastnames();
        this.birthdate = rcClient.getBirthdate();
        this.gender = rcClient.getGender();
        this.civilStatus = rcClient.getCivilStatus();
        this.phone = rcClient.getPhone();
        this.email = rcClient.getEmail();
        this.incomeMonth = rcClient.getIncomeMonth();
        this.addressHome = rcClient.getAddressHome();
        this.addressBusiness = rcClient.getAddressBusiness();
        this.category = rcClient.getCategory();
        this.occupation = rcClient.getOccupation();
        this.comment = rcClient.getComment();
    }

    public static RcClientDto getDto(RcClient rcClient){
        return RcClientDto.builder().
                id(rcClient.getId()).
                dni(rcClient.getDni()).
                names(rcClient.getNames()).
                lastnames(rcClient.getLastnames()).
                birthdate(rcClient.getBirthdate()).
                gender(rcClient.getGender()).
                civilStatus(rcClient.getCivilStatus()).
                phone(rcClient.getPhone()).
                email(rcClient.getEmail()).
                incomeMonth(rcClient.getIncomeMonth()).
                addressHome(rcClient.getAddressHome()).
                addressBusiness(rcClient.getAddressBusiness()).
                category(rcClient.getCategory()).
                occupation(rcClient.getOccupation()).
                comment(rcClient.getComment()).build();
    }

    @JsonIgnore
    public RcClient getEntity(){
        return RcClient.builder().
                dni(this.dni).
                names(this.names).
                lastnames(this.lastnames).
                birthdate(this.birthdate).
                gender(this.gender).
                civilStatus(this.civilStatus).
                phone(this.phone).
                email(this.email).
                incomeMonth(this.incomeMonth).
                addressHome(this.addressHome).
                addressBusiness(this.addressBusiness).
                category(this.category).
                occupation(this.occupation).
                comment(this.comment).build();
    }
}

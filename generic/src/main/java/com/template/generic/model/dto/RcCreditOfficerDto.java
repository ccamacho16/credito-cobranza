package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.AmBranch;
import com.template.generic.model.MuUser;
import com.template.generic.model.RcCreditOfficer;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RcCreditOfficerDto extends AbstractAuditableDto {
    private Long id;
    private String dni;
    private String names;
    private String lastnames;
    private String phone;
    private String email;
    private String address;

    private Long idBranch;

    private Long idUser;
    private String usernameUser;

    public RcCreditOfficerDto(RcCreditOfficer rcCreditOfficer){
        this.id = rcCreditOfficer.getId();
        this.dni = rcCreditOfficer.getDni();
        this.names = rcCreditOfficer.getNames();
        this.lastnames = rcCreditOfficer.getLastnames();
        this.phone = rcCreditOfficer.getPhone();
        this.email = rcCreditOfficer.getEmail();
        this.address = rcCreditOfficer.getAddress();

        this.idBranch = rcCreditOfficer.getBranch().getId();

        if (rcCreditOfficer.getUser() != null){
            this.idUser = rcCreditOfficer.getUser().getId();
            this.usernameUser = rcCreditOfficer.getUser().getUsername();
        }
    }

    public static RcCreditOfficerDto getDto(RcCreditOfficer rcCreditOfficer){
        return RcCreditOfficerDto.builder().
                id(rcCreditOfficer.getId()).
                dni(rcCreditOfficer.getDni()).
                names(rcCreditOfficer.getNames()).
                lastnames(rcCreditOfficer.getLastnames()).
                phone(rcCreditOfficer.getPhone()).
                email(rcCreditOfficer.getEmail()).
                address(rcCreditOfficer.getAddress()).build();
    }

    @JsonIgnore
    public RcCreditOfficer getEntity(){
        return RcCreditOfficer.builder().
                dni(this.dni).
                names(this.names).
                lastnames(this.lastnames).
                phone(this.phone).
                email(this.email).
                address(this.address).build();
    }
}

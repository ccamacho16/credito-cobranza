package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.MuUser;
import com.template.generic.model.enums.UserStatusEnum;
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
public class MuUserDto extends AbstractAuditableDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private UserStatusEnum status;

    private Long idRole;
    private String nameRole;

    private Long idBranch;
    private String nameBranch;

    public MuUserDto(MuUser muUser){
        this.id = muUser.getId();
        this.name = muUser.getName();
        this.username = muUser.getUsername();
        this.password = muUser.getPassword();
        this.email = muUser.getEmail();
        this.status = muUser.getStatus();

        if (muUser.getRole() != null){
            this.idRole = muUser.getRole().getId();
            this.nameRole = muUser.getRole().getName();
        }

        if (muUser.getBranch() != null){
            this.idBranch = muUser.getBranch().getId();
            this.nameBranch = muUser.getBranch().getName();
        }
    }
    public static MuUserDto getDto(MuUser muUser){
        return MuUserDto.builder().
                id(muUser.getId()).
                name(muUser.getName()).
                username(muUser.getUsername()).
                password(muUser.getPassword()).
                email(muUser.getEmail()).
                status(muUser.getStatus()).build();
    }

    @JsonIgnore
    public MuUser getEntity(){
        return MuUser.builder().
                name(this.name).
                username(this.username).
                password(this.password).
                email(this.email).
                status(this.status).build();

    }

}

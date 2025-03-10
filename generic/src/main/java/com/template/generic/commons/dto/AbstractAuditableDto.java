package com.template.generic.commons.dto;

import com.template.generic.model.AuditableEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractAuditableDto implements Serializable, Cloneable{
    protected Date createdDate;
    protected Date modifiedDate;
    protected String createdBy;
    protected String modifiedBy;
    protected Long version;
    protected boolean deleted;

    public AbstractAuditableDto(AuditableEntity entity) {
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.createdBy = entity.getCreatedBy();
        this.modifiedBy = entity.getModifiedBy();
        this.version = entity.getVersion();
        this.deleted = entity.isDeleted();
    }
}

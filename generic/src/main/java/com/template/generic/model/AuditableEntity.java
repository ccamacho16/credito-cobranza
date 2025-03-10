package com.template.generic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AuditableEntity implements Serializable, Cloneable {
    @CreatedDate
    protected Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    protected Date modifiedDate;

    @JsonIgnore
    @CreatedBy
    protected String createdBy;

    @JsonIgnore
    @LastModifiedBy
    protected String modifiedBy;

    @JsonIgnore
    @Version
    protected  Long version;

    @JsonIgnore
    protected boolean deleted = false;

    public Object clone()throws CloneNotSupportedException{
        return (AuditableEntity)super.clone();
    }
}

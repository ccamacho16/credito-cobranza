package com.template.generic.model;

import jakarta.persistence.*;
import lombok.*;

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
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mu_domain_value")
public class MuDomainValue extends AuditableEntity{
    @Id
    @SequenceGenerator(name = "SEQ_DOMAIN_VALUE_ID_GNRT", sequenceName = "SEQ_DOMAIN_VALUE_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOMAIN_VALUE_ID_GNRT")
    @Column(name = "id")
    private Long id;

    @Column(name = "value", length = 200, nullable = false)
    private String value;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @JoinColumn(name = "id_domain", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MuDomain domain;
}

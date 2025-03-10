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
@Table(name = "mu_parameter_group")
public class MuParameterGroup extends AuditableEntity{
    @Id
    @SequenceGenerator(name = "SEQ_PARAM_GROUP_ID_GNRT", sequenceName = "SEQ_PARAM_GROUP_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARAM_GROUP_ID_GNRT")
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name", nullable = false, length = 100)
    private String groupName;

    @Column(name = "description", nullable = false)
    private String description;
}

package com.template.generic.model;

import com.template.generic.model.enums.ParameterTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mu_parameter")
public class MuParameter extends AuditableEntity{
    @Id
    @SequenceGenerator(name = "SEQ_PARAMETER_ID_GNRT", sequenceName = "SEQ_PARAMETER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARAMETER_ID_GNRT")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "string_value", length = 1024)
    private String stringValue;


    @Column(name = "bool_value")
    private Boolean boolValue;

    @Column(name = "number_value")
    private BigDecimal numberValue;

    @Column(name = "date_value")
    private Date dateValue;

    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "lob_value")
    private String lobValue;

    @Column(name = "parameter_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParameterTypeEnum parameterType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parameter_group", nullable = false)
    private MuParameterGroup muParameterGroup;
}

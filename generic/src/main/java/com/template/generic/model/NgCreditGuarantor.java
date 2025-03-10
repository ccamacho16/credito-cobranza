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
 * 10.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ng_credit_guarantor")
public class NgCreditGuarantor extends AuditableEntity{
    @Id
    @SequenceGenerator(name = "SEQ_CREDIT_GUAR_ID_GNRT", sequenceName = "SEQ_CREDIT_GUAR_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDIT_GUAR_ID_GNRT")
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "id_credit", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private NgCredit credit;

    @JoinColumn(name = "id_guarantor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RcGuarantor guarantor;
}

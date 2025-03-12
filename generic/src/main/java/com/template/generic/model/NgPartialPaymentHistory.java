package com.template.generic.model;

import com.template.generic.model.enums.DestinationPartialPaymentEnum;
import jakarta.persistence.*;
import lombok.*;

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
 * 11.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ng_partial_payment_history")
public class NgPartialPaymentHistory extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_PAR_PAY_HIS_GNRT", sequenceName = "SEQ_PAR_PAY_HIS_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAR_PAY_HIS_ID_GNRT")
    private Long id;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "destination", nullable = false)
    private DestinationPartialPaymentEnum destination; // capital - interes

    @JoinColumn(name = "id_credit_detail", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private NgCreditDetail creditDetail;
}

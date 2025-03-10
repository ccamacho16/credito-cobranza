package com.template.generic.model;

import com.template.generic.model.enums.CreditShareEnum;
import com.template.generic.model.enums.CreditStatusEnum;
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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ng_credit_detail")
public class NgCreditDetail extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_CREDIT_DET_GNRT", sequenceName = "SEQ_CREDIT_DET_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDIT_DET_ID_GNRT")
    private Long id;

    @Column(name = "share_number", nullable = false)
    private Byte shareNumber; //numero de cuota

    @Column(name = "share_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date shareDate;

    @Column(name = "capital", nullable = false)
    private Integer capital;  //  capital

    @Column(name = "interest", nullable = false)
    private Integer interest;  // interes

    @Column(name = "capital_balance", nullable = false)
    private Integer capitalBalance;  // saldo capital

    @Column(name = "payment_capital", nullable = false)
    private Integer paymentCapital;  // abono capital

    @Column(name = "payment_interest", nullable = false)
    private Integer paymentInterest;  // abono interes

    @Column(name = "total", nullable = false)
    private Integer total;  // total

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "payment_late", nullable = false)
    private Integer paymentLate;  // pago por mora

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CreditShareEnum status;  //PENDIENTE - PARCIAL -PAGADO

}

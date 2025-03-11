package com.template.generic.model;

import com.template.generic.model.enums.CreditStatusEnum;
import com.template.generic.model.enums.CreditTypeEnum;
import jakarta.persistence.*;
import lombok.*;

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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ng_credit")
public class NgCredit extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_CREDIT_ID_GNRT", sequenceName = "SEQ_CREDIT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDIT_ID_GNRT")
    private Long id;

    @Column(name = "generation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generationDate;

    @Column(name = "reference_personal", length = 150)
    private String referencePersonal;

    @Column(name = "reference_job", length = 150)
    private String referenceJob;

    @Column(name = "frequency_payment", nullable = false, length = 25)
    private String frequencyPayment;  //MENSUAL - QUINCENAL -SEMANAL

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "capital_balance", nullable = false)
    private Integer capitalBalance;  // saldo capital

    @Column(name = "term", nullable = false)
    private Byte term; //plazo

    @Column(name = "number_shares", nullable = false)
    private Byte numberShares; //numero de cuotas

    @Column(name = "warranty", nullable = false, length = 50)
    private String warranty; //garantia

    @Column(name = "comment_warranty", length = 500)
    private String commentWarranty;

    @Column(name = "interest", nullable = false, precision = 2, scale = 1)
    private BigDecimal interest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CreditStatusEnum status;  //SOLICTIADO, APROBADO, ACTIVO, ETC.

    @Column(name = "disbursement_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date disbursementDate; //fecha de desembolso

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CreditTypeEnum type;  //NUEVO-REPROGRAMACION-REFINANCIAMIENTO

    @Column(name = "credit_origin", nullable = true)
    private Long creditOrigin;

    @Column(name = "comment", length = 250)
    private String comment; // comentario del credito en general.

    @Column(name = "note", length = 250)
    private String note;  // anotaciones de trasabilidad.

    @JoinColumn(name = "id_client", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RcClient client;

    @JoinColumn(name = "id_credit_officer", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RcCreditOfficer creditOfficer;
}

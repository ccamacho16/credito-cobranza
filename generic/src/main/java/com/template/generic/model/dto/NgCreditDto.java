package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.NgCredit;
import com.template.generic.model.RcClient;
import com.template.generic.model.RcCreditOfficer;
import com.template.generic.model.RcGuarantor;
import com.template.generic.model.enums.CreditStatusEnum;
import com.template.generic.model.enums.CreditTypeEnum;
import com.template.generic.model.enums.CreditTypePlanPaymentEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NgCreditDto extends AbstractAuditableDto {
    private Long id;
    private Date generationDate;
    private String referencePersonal;
    private String referenceJob;
    private CreditTypePlanPaymentEnum typePlanPayment; // tipo de plan de pago
    private String frequencyPayment;  //MENSUAL - QUINCENAL -SEMANAL
    private Integer amount;
    private Integer capitalBalance;  // saldo capital
    private Byte term; //plazo
    private Byte numberShares; //numero de cuotas
    private String warranty; //garantia
    private String commentWarranty;
    private BigDecimal interest;
    private CreditStatusEnum status;  //SOLICTIADO, APROBADO, ACTIVO, ETC.
    private Date disbursementDate; //fecha de desembolso
    private CreditTypeEnum type;  //NUEVO-REPROGRAMACION-REFINANCIAMIENTO
    private NgCreditDto creditOriginDto;
    private String comment; // comentario del credito en general.
    private String note;  // anotaciones de trasabilidad.

    private Long idClient;
    private String descriptionClient;

    private Long idCreditOfficer;
    private String nameCreditOfficer;

    private Long idBranch;

    private Set<NgCreditDetailDto> creditDetailsDto;

    private Set<RcGuarantorDto> guarantosDto; //lista de garantes, en el caso de que aplique.

    public NgCreditDto(NgCredit ngCredit){
        this.id = ngCredit.getId();
        this.generationDate = ngCredit.getGenerationDate();
        this.referencePersonal = ngCredit.getReferencePersonal();
        this.referenceJob = ngCredit.getReferenceJob();
        this.typePlanPayment = ngCredit.getTypePlanPayment();
        this.frequencyPayment = ngCredit.getFrequencyPayment();
        this.amount = ngCredit.getAmount();
        this.capitalBalance = ngCredit.getCapitalBalance();
        this.term = ngCredit.getTerm();
        this.numberShares = ngCredit.getNumberShares();
        this.warranty = ngCredit.getWarranty();
        this.commentWarranty = ngCredit.getCommentWarranty();
        this.interest = ngCredit.getInterest();
        this.status = ngCredit.getStatus();
        this.disbursementDate = ngCredit.getDisbursementDate();
        this.type = ngCredit.getType();
        if (ngCredit.getCreditOrigin() != null)
            this.creditOriginDto = NgCreditDto.getDto(ngCredit.getCreditOrigin());
        this.comment = ngCredit.getComment();
        this.note = ngCredit.getNote();

        this.idClient = ngCredit.getClient().getId();
        this.descriptionClient = ngCredit.getClient().getDni() + " - " + ngCredit.getClient().getNames() + " " + ngCredit.getClient().getLastnames();

        this.idCreditOfficer = ngCredit.getCreditOfficer().getId();
        this.nameCreditOfficer = ngCredit.getCreditOfficer().getNames() + " " + ngCredit.getCreditOfficer().getLastnames();

        this.idBranch = ngCredit.getBranch().getId();

        this.creditDetailsDto = ngCredit.getCreditDetails().stream()
                .map(NgCreditDetailDto::new) // Asumiendo que tienes un constructor en NgCreditDetailDto que recibe NgCreditDetail
                .collect(Collectors.toSet());
    }

    public static NgCreditDto getDto(NgCredit ngCredit){
        return NgCreditDto.builder().
                id(ngCredit.getId()).
                generationDate(ngCredit.getGenerationDate()).
                referencePersonal(ngCredit.getReferencePersonal()).
                referenceJob(ngCredit.getReferenceJob()).
                typePlanPayment(ngCredit.getTypePlanPayment()).
                frequencyPayment(ngCredit.getFrequencyPayment()).
                amount(ngCredit.getAmount()).
                capitalBalance(ngCredit.getCapitalBalance()).
                term(ngCredit.getTerm()).
                numberShares(ngCredit.getNumberShares()).
                warranty(ngCredit.getWarranty()).
                commentWarranty(ngCredit.getCommentWarranty()).
                interest(ngCredit.getInterest()).
                status(ngCredit.getStatus()).
                disbursementDate(ngCredit.getDisbursementDate()).
                type(ngCredit.getType()).
                creditOriginDto(ngCredit.getCreditOrigin()!=null ? NgCreditDto.getDto(ngCredit.getCreditOrigin()) : null).
                comment(ngCredit.getComment()).
                note(ngCredit.getNote()).build();

    }

    @JsonIgnore
    public NgCredit getEntity(){
        return NgCredit.builder().
                generationDate(this.generationDate).
                referencePersonal(this.referencePersonal).
                referenceJob(this.referenceJob).
                typePlanPayment(this.typePlanPayment).
                frequencyPayment(this.frequencyPayment).
                amount(this.amount).
                capitalBalance(this.capitalBalance).
                term(this.term).
                numberShares(this.numberShares).
                warranty(this.warranty).
                commentWarranty(this.commentWarranty).
                interest(this.interest).
                status(this.status).
                disbursementDate(this.disbursementDate).
                type(this.type).
                comment(this.comment).
                note(this.note).build();
    }
}

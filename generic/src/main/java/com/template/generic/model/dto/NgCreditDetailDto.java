package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.NgCredit;
import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.enums.CreditShareEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NgCreditDetailDto extends AbstractAuditableDto {
    private Long id;
    private Byte shareNumber; //numero de cuota
    private Date shareDate;
    private Integer capital;  //  capital
    private Integer interest;  // interes
    private Integer capitalBalance;  // saldo capital
    private Integer paymentCapital;  // abono capital
    private Integer paymentInterest;  // abono interes
    private Integer total;  // total
    private Date paymentDate;
    private Integer paymentLate;  // pago por mora
    private CreditShareEnum status;  //PENDIENTE - PARCIAL -PAGADO

    public NgCreditDetailDto(NgCreditDetail ngCreditDetail){
        this.id = ngCreditDetail.getId();
        this.shareNumber = ngCreditDetail.getShareNumber();
        this.shareDate = ngCreditDetail.getShareDate();
        this.capital = ngCreditDetail.getCapital();
        this.interest = ngCreditDetail.getInterest();
        this.capitalBalance = ngCreditDetail.getCapitalBalance();
        this.paymentCapital = ngCreditDetail.getPaymentCapital();
        this.paymentInterest = ngCreditDetail.getPaymentInterest();
        this.total = ngCreditDetail.getTotal();
        this.paymentDate = ngCreditDetail.getPaymentDate();
        this.paymentLate = ngCreditDetail.getPaymentLate();
        this.status = ngCreditDetail.getStatus();
    }

    public static NgCreditDetailDto getDto(NgCreditDetail ngCreditDetail){
        return NgCreditDetailDto.builder().
                id(ngCreditDetail.getId()).
                shareNumber(ngCreditDetail.getShareNumber()).
                shareDate(ngCreditDetail.getShareDate()).
                capital(ngCreditDetail.getCapital()).
                interest(ngCreditDetail.getInterest()).
                capitalBalance(ngCreditDetail.getCapitalBalance()).
                paymentCapital(ngCreditDetail.getPaymentCapital()).
                paymentInterest(ngCreditDetail.getPaymentInterest()).
                total(ngCreditDetail.getTotal()).
                paymentDate(ngCreditDetail.getPaymentDate()).
                paymentLate(ngCreditDetail.getPaymentLate()).
                status(ngCreditDetail.getStatus()).build();

    }
    @JsonIgnore
    public NgCreditDetail getEntity(){
        return NgCreditDetail.builder().
                shareNumber(this.shareNumber).
                shareDate(this.shareDate).
                capital(this.capital).
                interest(this.interest).
                capitalBalance(this.capitalBalance).
                paymentCapital(this.paymentCapital).
                paymentInterest(this.paymentInterest).
                total(this.total).
                paymentDate(this.paymentDate).
                paymentLate(this.paymentLate).
                status(this.status).build();
    }
}

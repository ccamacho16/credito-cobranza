package com.template.generic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.NgPartialPaymentHistory;
import com.template.generic.model.enums.DestinationPartialPaymentEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class NgPartialPaymentHistoryDto extends AbstractAuditableDto {
    private Long id;
    private Date paymentDate;
    private Integer amount;
    private DestinationPartialPaymentEnum destination; // capital - interes

    public NgPartialPaymentHistoryDto(NgPartialPaymentHistory ngPartialPaymentHistory){
        this.id = ngPartialPaymentHistory.getId();
        this.paymentDate = ngPartialPaymentHistory.getPaymentDate();
        this.amount = ngPartialPaymentHistory.getAmount();
        this.destination = ngPartialPaymentHistory.getDestination();
    }

    public static NgPartialPaymentHistoryDto getDto(NgPartialPaymentHistory ngPartialPaymentHistory){
        return NgPartialPaymentHistoryDto.builder().
                id(ngPartialPaymentHistory.getId()).
                paymentDate(ngPartialPaymentHistory.getPaymentDate()).
                amount(ngPartialPaymentHistory.getAmount()).
                destination(ngPartialPaymentHistory.getDestination()).build();
    }

    @JsonIgnore
    public NgPartialPaymentHistory getEntity(){
        return NgPartialPaymentHistory.builder().
                paymentDate(this.paymentDate).
                amount(this.amount).
                destination(this.destination).build();
    }
}

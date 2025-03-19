package com.template.generic.model.dto.select;

import com.template.generic.commons.dto.AbstractAuditableDto;
import com.template.generic.model.NgCredit;
import com.template.generic.model.dto.NgCreditDto;
import com.template.generic.model.enums.CreditStatusEnum;
import com.template.generic.model.enums.CreditTypeEnum;
import com.template.generic.model.enums.CreditTypePlanPaymentEnum;
import com.template.generic.model.enums.FrecuencyPaymentEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
 * 19.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCabDto extends AbstractAuditableDto {
    private Long id;
    private Date generationDate;
    private FrecuencyPaymentEnum frequencyPayment;  //MENSUAL - QUINCENAL -SEMANAL
    private Byte term; //plazo
    private Integer amount;
    private CreditTypeEnum type;  //NUEVO-REPROGRAMACION-REFINANCIAMIENTO
    private CreditStatusEnum status;  //SOLICTIADO, APROBADO, ACTIVO, ETC.


    private String descriptionClient;
    private String nameCreditOfficer;

    public CreditCabDto(NgCredit ngCredit){
        this.id = ngCredit.getId();
        this.generationDate = ngCredit.getGenerationDate();
        this.frequencyPayment = ngCredit.getFrequencyPayment();
        this.term = ngCredit.getTerm();
        this.amount = ngCredit.getAmount();
        this.type = ngCredit.getType();
        this.status = ngCredit.getStatus();

        this.descriptionClient = ngCredit.getClient().getDni() + " - " + ngCredit.getClient().getNames() + " " + ngCredit.getClient().getLastnames();

        this.nameCreditOfficer = ngCredit.getCreditOfficer().getNames() + " " + ngCredit.getCreditOfficer().getLastnames();
    }
}

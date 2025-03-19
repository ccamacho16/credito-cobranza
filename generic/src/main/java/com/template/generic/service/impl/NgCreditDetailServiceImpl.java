package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.NgCredit;
import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.dto.NgCreditDetailDto;
import com.template.generic.model.enums.CreditShareEnum;
import com.template.generic.repository.NgCreditDetailRepository;
import com.template.generic.repository.NgCreditRepository;
import com.template.generic.service.NgCreditDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
 * 13.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class NgCreditDetailServiceImpl implements NgCreditDetailService {

    @Autowired
    private NgCreditRepository ngCreditRepository;

    @Autowired
    private NgCreditDetailRepository ngCreditDetailRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createFromList(Set<NgCreditDetailDto> list, Long idCredit) {

        NgCredit credit = this.ngCreditRepository.findById(idCredit)
                .orElseThrow(()->new OperationException("No existe un Crédito con id: "+idCredit));

        for (NgCreditDetailDto dto : list){
            NgCreditDetail creditDetail = dto.getEntity();
            creditDetail.setStatus(CreditShareEnum.PENDIENTE);
            creditDetail.setCredit(credit);
            this.ngCreditDetailRepository.save(creditDetail);
        }
    }


}

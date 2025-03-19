package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.NgCredit;
import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.NgCreditGuarantor;
import com.template.generic.model.RcGuarantor;
import com.template.generic.model.dto.NgCreditDetailDto;
import com.template.generic.model.dto.RcGuarantorDto;
import com.template.generic.model.enums.CreditShareEnum;
import com.template.generic.repository.NgCreditGuarantorRepository;
import com.template.generic.repository.NgCreditRepository;
import com.template.generic.repository.RcGuarantorRepository;
import com.template.generic.service.NgCreditGuarantorService;
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
public class NgCreditGuarantorServiceImpl implements NgCreditGuarantorService {

    @Autowired
    private NgCreditRepository ngCreditRepository;
    @Autowired
    private RcGuarantorRepository rcGuarantorRepository;
    @Autowired
    private NgCreditGuarantorRepository ngCreditGuarantorRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createFromList(Set<Long> list, Long idCredit) {
        NgCredit credit = this.ngCreditRepository.findById(idCredit)
                .orElseThrow(()->new OperationException("No existe un Crédito con id: "+idCredit));

        for (Long idGuarantor : list){
            RcGuarantor guarantor = this.rcGuarantorRepository.findById(idGuarantor)
                    .orElseThrow(()->new OperationException("No existe un Garante con id: "+idCredit));

            this.ngCreditGuarantorRepository.save(NgCreditGuarantor.builder().
                    credit(credit).
                    guarantor(guarantor).build());
        }
    }


}

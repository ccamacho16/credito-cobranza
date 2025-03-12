package com.template.generic.service.impl;

import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.dto.NgCreditDto;
import com.template.generic.repository.NgCreditDetailRepository;
import com.template.generic.repository.NgCreditGuarantorRepository;
import com.template.generic.repository.NgCreditRepository;
import com.template.generic.service.NgCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service
public class NgCreditServiceImpl implements NgCreditService {

    @Autowired
    private NgCreditRepository ngCreditRepository;
    @Autowired
    private NgCreditDetailRepository ngCreditDetailRepository;
    @Autowired
    private NgCreditGuarantorRepository ngCreditGuarantorRepository;


    @Override
    public List<NgCreditDetail> generatePaymentPlan(NgCreditDto dto) {
        return null;
    }
}

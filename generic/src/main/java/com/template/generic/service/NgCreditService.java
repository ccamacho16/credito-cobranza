package com.template.generic.service;

import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.dto.NgCreditDetailDto;
import com.template.generic.model.dto.NgCreditDto;
import com.template.generic.model.dto.select.CreditCabDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface NgCreditService {
    Page<CreditCabDto> listByFilter(String filter, Pageable pageable);
    List<NgCreditDetailDto> generatePaymentPlan(NgCreditDto dto);
    NgCreditDto create(NgCreditDto dto);
    NgCreditDto findById(Long id);
}

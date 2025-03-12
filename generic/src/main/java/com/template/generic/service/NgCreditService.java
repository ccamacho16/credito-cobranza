package com.template.generic.service;

import com.template.generic.model.NgCreditDetail;
import com.template.generic.model.dto.NgCreditDto;

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
    List<NgCreditDetail> generatePaymentPlan(NgCreditDto dto);
}

package com.template.generic.service;

import com.template.generic.model.MuDomain;
import com.template.generic.model.MuDomainValue;
import com.template.generic.model.dto.MuDomainValueDto;
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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuDomainService {
    Page<MuDomainValueDto> findMuDomainValueByDomain(String domainId, String filter, Pageable pageable);

    List<MuDomain> findAllDomain();

    MuDomain findMuDomainById(String id);

    MuDomain createMuDomainDefault(MuDomain domain);

    MuDomainValue createMuDomainValue(MuDomainValueDto domainValueDto);

    void voidCreateMuDomainValue(MuDomainValueDto domainValueDto);

    MuDomainValue updateMuDomainValue(MuDomainValueDto domainValueDto, Long domainValueId);

    void deleteMuDomainValue(Long domainValueId);

    List<MuDomainValueDto> findMuDomainValueByDomain(String domainId);
}

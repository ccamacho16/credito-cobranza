package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.MuDomain;
import com.template.generic.model.MuDomainValue;
import com.template.generic.model.dto.MuDomainValueDto;
import com.template.generic.repository.MuDomainRepository;
import com.template.generic.repository.MuDomainValueRepository;
import com.template.generic.service.MuDomainService;
import com.template.generic.util.FormatUtil;
import com.template.generic.util.StringUtil;
import com.template.generic.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
@Service
public class MuDomainServiceImpl implements MuDomainService {
    @Autowired
    private MuDomainRepository muDomainRespository;

    @Autowired
    private MuDomainValueRepository muDomainValueRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<MuDomainValueDto> findMuDomainValueByDomain(String domainId, String filter, Pageable pageable) {
        filter = (StringUtil.isEmptyOrNull(filter))? "%": "%" + filter + "%";
        return this.muDomainValueRepository.findDomainValueByDomain(domainId, filter, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MuDomain> findAllDomain() {
        return this.muDomainRespository.findAllByDeletedFalse();
    }

    @Override
    public MuDomain findMuDomainById(String id) {
        return this.muDomainRespository.findById(id).orElse(null);
    }

    @Override
    public MuDomain createMuDomainDefault(MuDomain domain) {
        Optional<MuDomain> domainOpt = this.muDomainRespository.findById(domain.getId());
        if (domainOpt.isPresent())
            return domainOpt.get();

        domain = muDomainRespository.save(domain);
        return domain;
    }

    @Override
    public MuDomainValue createMuDomainValue(MuDomainValueDto domainValueDto) {
        ValidationUtil.throwExceptionIfInvalidText("Valor", domainValueDto.getValue(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidText("Descripcion", domainValueDto.getDescription(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidText("Dominio Id", domainValueDto.getIdDomain(), true, 1, 200);

        Optional<MuDomainValue> domainValueOpt = this.muDomainValueRepository.findMuDomainValuesByDeletedIsFalseAndValue(domainValueDto.getValue().trim());

        if (domainValueOpt.isPresent())
            throw new OperationException(FormatUtil.yaRegistrado("Valor de dominio", "Valor", domainValueDto.getValue()));

        MuDomain domain = muDomainRespository.findById(domainValueDto.getIdDomain()).orElseThrow(() ->
                new OperationException(FormatUtil.noRegistrado("Domain", domainValueDto.getIdDomain())));

        return this.muDomainValueRepository.save(MuDomainValue.builder()
                .value(domainValueDto.getValue().trim())
                .description(domainValueDto.getDescription())
                .domain(domain)
                .build());
    }

    @Override
    public void voidCreateMuDomainValue(MuDomainValueDto domainValueDto) {
        ValidationUtil.throwExceptionIfInvalidText("Valor", domainValueDto.getValue(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidText("Descripcion", domainValueDto.getDescription(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidText("Dominio Id", domainValueDto.getIdDomain(), true, 1, 200);

        MuDomain domain = muDomainRespository.findById(domainValueDto.getIdDomain()).orElseThrow(() ->
                new OperationException(FormatUtil.noRegistrado("Domain", domainValueDto.getIdDomain())));

        Optional<MuDomainValue> domainValueOpt = this.muDomainValueRepository.findByValueAndDomain(domainValueDto.getValue().trim(), domain);

        if (!domainValueOpt.isPresent()) {

            this.muDomainValueRepository.save(MuDomainValue.builder()
                    .value(domainValueDto.getValue().trim())
                    .description(domainValueDto.getDescription())
                    .domain(domain)
                    .build());
        }
    }

    @Override
    public MuDomainValue updateMuDomainValue(MuDomainValueDto domainValueDto, Long domainValueId) {
        ValidationUtil.throwExceptionIfInvalidText("Value", domainValueDto.getValue(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidText("Descripcion", domainValueDto.getDescription(), true, 1, 200);
        ValidationUtil.throwExceptionIfInvalidNumber("Valor Dominio Id", domainValueId, true, 0L);

        MuDomainValue domainValue = this.muDomainValueRepository.findById(domainValueId)
                .orElseThrow(() -> new OperationException(FormatUtil.noRegistrado("Domain Value", domainValueId)));

        Optional<MuDomainValue> domainValueOpt = this.muDomainValueRepository.findByDeletedFalseAndValue(domainValueDto.getValue().trim());
        if (domainValueOpt.isPresent() && !domainValueOpt.get().getId().equals(domainValue.getId()))
            throw new OperationException(FormatUtil.yaRegistrado("Domain Value", "Value", domainValueDto.getValue()));

        domainValue.setValue(domainValueDto.getValue());
        domainValue.setDescription(domainValueDto.getDescription());
        return this.muDomainValueRepository.save(domainValue);
    }

    @Override
    @Transactional
    public void deleteMuDomainValue(Long domainValueId) {
        MuDomainValue domainValue = this.muDomainValueRepository.findById(domainValueId)
                .orElseThrow(() -> new OperationException(FormatUtil.noRegistrado("Domain Value", domainValueId)));
        this.muDomainValueRepository.deleteDomainValue(domainValue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MuDomainValueDto> findMuDomainValueByDomain(String domainId) {
        return this.muDomainValueRepository.findDomainValueByDomain(domainId);
    }
}

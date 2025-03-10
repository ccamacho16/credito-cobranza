package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.MuParameter;
import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.dto.MuParameterDto;
import com.template.generic.repository.MuParameterGroupRepository;
import com.template.generic.repository.MuParameterRepository;
import com.template.generic.service.MuParameterService;
import com.template.generic.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class MuParameterServiceImpl implements MuParameterService {
    @Autowired
    private MuParameterRepository muParameterRepository;
    @Autowired
    private MuParameterGroupRepository muParameterGroupRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveParameter(MuParameter muParameter) {
        if (!this.muParameterRepository.findParametroByCodigo(muParameter.getCode()).isPresent()) {
            this.muParameterRepository.save(muParameter);
        }
    }

    @Override
    public List<MuParameterDto> listParametersByNameGroup(String nameGroup) {
        MuParameterGroup muParameterGroup = this.muParameterGroupRepository.findByGrupo(nameGroup).orElseThrow(() -> new OperationException(FormatUtil.noRegistrado("Grupo de Parámetro", nameGroup)));
        return this.muParameterRepository.getParametersByNameGroup(muParameterGroup);
    }

    @Override
    public MuParameterDto listParameterByGroupAndCode(String nameGroup, String code) {
        MuParameterGroup muParameterGroup = this.muParameterGroupRepository.findByGrupo(nameGroup).orElseThrow(() -> new OperationException(FormatUtil.noRegistrado("Grupo de Parámetro", nameGroup)));
        return this.muParameterRepository.getParametersByNameGroupAndCode(muParameterGroup, code);
    }
}

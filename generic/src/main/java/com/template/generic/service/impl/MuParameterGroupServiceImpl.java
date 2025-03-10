package com.template.generic.service.impl;

import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.dto.MuParameterGroupDto;
import com.template.generic.repository.MuParameterGroupRepository;
import com.template.generic.service.MuParameterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
public class MuParameterGroupServiceImpl implements MuParameterGroupService {

    @Autowired
    private MuParameterGroupRepository muParameterGroupRepository;

    @Override
    public List<MuParameterGroupDto> getParameterGroupList() {
        return this.muParameterGroupRepository.listaGruposParametro();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MuParameterGroup> findByGrupo(String group) {
        return this.muParameterGroupRepository.findByGrupo(group);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(MuParameterGroup group) {
        this.muParameterGroupRepository.save(group);
    }
}

package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.AmBranch;
import com.template.generic.model.RcClient;
import com.template.generic.model.dto.AmBranchDto;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.repository.AmBranchRepository;
import com.template.generic.service.AmBranchService;
import com.template.generic.util.FormatUtil;
import com.template.generic.util.StringUtil;
import com.template.generic.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
 * 17.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class AmBranchServiceImpl implements AmBranchService {
    @Autowired

    private AmBranchRepository amBranchRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AmBranchDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.amBranchRepository.pageWithFilter(filter, pageable, AmBranchDto.class);
    }

    @Override
    public AmBranchDto findById(Long id) {
        AmBranch amBranch = this.amBranchRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe una Sucursal con id: "+id));
        return AmBranchDto.getDto(amBranch);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AmBranchDto create(AmBranchDto amBranchDto) {
        this.validate(amBranchDto, true);
        AmBranch amBranch = amBranchDto.getEntity();
        this.amBranchRepository.save(amBranch);
        return AmBranchDto.getDto(amBranch);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AmBranchDto update(AmBranchDto amBranchDto) {
        this.validate(amBranchDto, false);
        AmBranch amBranch = this.amBranchRepository.findById(amBranchDto.getId())
                .orElseThrow(()->new OperationException("No existe una Sucursal con id: "+amBranchDto.getId()));

        amBranch.setName(amBranchDto.getName());
        amBranch.setDescription(amBranchDto.getDescription());
        amBranch.setAddress(amBranchDto.getAddress());
        amBranch.setPhone(amBranchDto.getPhone());

        this.amBranchRepository.save(amBranch);
        return AmBranchDto.getDto(amBranch);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        AmBranch amBranch = this.amBranchRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe una Sucursal con id: "+id));
        amBranch.setDeleted(true);
        this.amBranchRepository.save(amBranch);
    }

    private void validate(AmBranchDto dto, boolean validName){
        if (dto == null){
            throw new OperationException("La sucursal recibida es igual a NULL");
        }else{

            ValidationUtil.throwExceptionRequiredIfBlank("Nombre", dto.getName());
            ValidationUtil.throwExceptionRequiredIfBlank("Descripción", dto.getDescription());
            ValidationUtil.throwExceptionRequiredIfBlank("Dirección", dto.getAddress());
            ValidationUtil.throwExceptionRequiredIfBlank("Telefono", dto.getPhone());

            if (validName && this.amBranchRepository.findByName(dto.getName()).isPresent()){
                throw new OperationException(FormatUtil.yaRegistrado("Sucursal", "Nombre", dto.getName()));
            }
        }
    }
}

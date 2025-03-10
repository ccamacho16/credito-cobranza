package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.RcClient;
import com.template.generic.model.RcGuarantor;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcGuarantorDto;
import com.template.generic.repository.RcClientRepository;
import com.template.generic.repository.RcGuarantorRepository;
import com.template.generic.service.RcGuarantorService;
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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class RcGuarantorServiceImpl implements RcGuarantorService {

    @Autowired
    private RcGuarantorRepository rcGuarantorRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<RcGuarantorDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.rcGuarantorRepository.pageWithFilter(filter, pageable, RcGuarantorDto.class);
    }

    @Override
    public RcGuarantorDto findById(Long id) {
        RcGuarantor rcGuarantor = this.rcGuarantorRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Garante con id: "+id));
        return RcGuarantorDto.getDto(rcGuarantor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcGuarantorDto create(RcGuarantorDto rcGuarantorDto) {
        this.validate(rcGuarantorDto, true);
        RcGuarantor rcGuarantor = rcGuarantorDto.getEntity();
        this.rcGuarantorRepository.save(rcGuarantor);
        return RcGuarantorDto.getDto(rcGuarantor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcGuarantorDto update(RcGuarantorDto rcGuarantorDto) {
        this.validate(rcGuarantorDto, false);
        RcGuarantor rcGuarantor = this.rcGuarantorRepository.findById(rcGuarantorDto.getId())
                .orElseThrow(()->new OperationException("No existe un Garante con id: "+rcGuarantorDto.getId()));

        rcGuarantor.setDni(rcGuarantorDto.getDni());
        rcGuarantor.setNames(rcGuarantorDto.getNames());
        rcGuarantor.setLastnames(rcGuarantorDto.getLastnames());
        rcGuarantor.setBirthdate(rcGuarantorDto.getBirthdate());
        rcGuarantor.setCivilStatus(rcGuarantorDto.getCivilStatus());
        rcGuarantor.setPhone(rcGuarantorDto.getPhone());
        rcGuarantor.setAddressHome(rcGuarantorDto.getAddressHome());
        rcGuarantor.setAddressBusiness(rcGuarantorDto.getAddressBusiness());
        rcGuarantor.setRelationship(rcGuarantorDto.getRelationship());

        this.rcGuarantorRepository.save(rcGuarantor);
        return RcGuarantorDto.getDto(rcGuarantor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        RcGuarantor rcGuarantor = this.rcGuarantorRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Garante con id: "+id));
        rcGuarantor.setDeleted(true);
        this.rcGuarantorRepository.save(rcGuarantor);
    }

    private void validate(RcGuarantorDto dto, boolean validDni){
        if (dto == null){
            throw new OperationException("El Garante recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionRequiredIfBlank("Dni", dto.getDni());
            ValidationUtil.throwExceptionRequiredIfBlank("Nombre", dto.getNames());
            ValidationUtil.throwExceptionRequiredIfBlank("Apellidos", dto.getLastnames());
            ValidationUtil.throwExceptionIfInvalidDate("Fecha de Nacimiento", dto.getBirthdate(), true);
            ValidationUtil.throwExceptionRequiredIfBlank("Estado Civil", dto.getCivilStatus());
            ValidationUtil.throwExceptionRequiredIfBlank("Telefono", dto.getPhone());
            ValidationUtil.throwExceptionRequiredIfBlank("Dirección Domicilio", dto.getAddressHome());
            ValidationUtil.throwExceptionRequiredIfBlank("Parentesco", dto.getRelationship());

            if (validDni && this.rcGuarantorRepository.findByDni(dto.getDni()).isPresent()){
                throw new OperationException(FormatUtil.yaRegistrado("Garante", "Dni", dto.getDni()));
            }
        }
    }
}

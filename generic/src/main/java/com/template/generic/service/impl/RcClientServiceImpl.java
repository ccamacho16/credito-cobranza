package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.RcClient;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.repository.RcClientRepository;
import com.template.generic.service.RcClientService;
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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class RcClientServiceImpl implements RcClientService {
    @Autowired
    private RcClientRepository rcClientRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<RcClientDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.rcClientRepository.pageWithFilter(filter, pageable, RcClientDto.class);
    }

    @Override
    public RcClientDto findById(Long id) {
        RcClient rcClient = this.rcClientRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Cliente con id: "+id));
        return RcClientDto.getDto(rcClient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcClientDto create(RcClientDto rcClientDto) {
        this.validate(rcClientDto, true);
        RcClient rcClient = rcClientDto.getEntity();
        this.rcClientRepository.save(rcClient);
        return RcClientDto.getDto(rcClient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcClientDto update(RcClientDto rcClientDto) {
        this.validate(rcClientDto, false);
        RcClient rcClient = this.rcClientRepository.findById(rcClientDto.getId())
                .orElseThrow(()->new OperationException("No existe un Cliente con id: "+rcClientDto.getId()));

        rcClient.setDni(rcClientDto.getDni());
        rcClient.setNames(rcClientDto.getNames());
        rcClient.setLastnames(rcClientDto.getLastnames());
        rcClient.setBirthdate(rcClientDto.getBirthdate());
        rcClient.setGender(rcClientDto.getGender());
        rcClient.setCivilStatus(rcClientDto.getCivilStatus());
        rcClient.setPhone(rcClientDto.getPhone());
        rcClient.setEmail(rcClientDto.getEmail());
        rcClient.setIncomeMonth(rcClientDto.getIncomeMonth());
        rcClient.setAddressHome(rcClientDto.getAddressHome());
        rcClient.setAddressBusiness(rcClientDto.getAddressBusiness());
        rcClient.setCategory(rcClientDto.getCategory());
        rcClient.setOccupation(rcClientDto.getOccupation());
        rcClient.setComment(rcClientDto.getComment());

        this.rcClientRepository.save(rcClient);
        return RcClientDto.getDto(rcClient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        RcClient rcClient = this.rcClientRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Cliente con id: "+id));
        rcClient.setDeleted(true);
        this.rcClientRepository.save(rcClient);
    }

    private void validate(RcClientDto dto, boolean validDni){
        if (dto == null){
            throw new OperationException("El cliente recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionRequiredIfBlank("Dni", dto.getDni());
            ValidationUtil.throwExceptionRequiredIfBlank("Nombre", dto.getNames());
            ValidationUtil.throwExceptionRequiredIfBlank("Apellidos", dto.getLastnames());
            ValidationUtil.throwExceptionIfInvalidDate("Fecha de Nacimiento", dto.getBirthdate(), true);
            ValidationUtil.throwExceptionRequiredIfBlank("Genero", dto.getGender());
            ValidationUtil.throwExceptionRequiredIfBlank("Estado Civil", dto.getCivilStatus());
            ValidationUtil.throwExceptionRequiredIfBlank("Telefono", dto.getPhone());
            ValidationUtil.throwExceptionIfInvalidNumber("Ingresos Mensuales", dto.getIncomeMonth(), true, 0);
            ValidationUtil.throwExceptionRequiredIfBlank("Dirección Domicilio", dto.getAddressHome());
            ValidationUtil.throwExceptionRequiredIfBlank("Categoría", dto.getCategory());
            ValidationUtil.throwExceptionRequiredIfBlank("Ocupación", dto.getOccupation());


            if (validDni && this.rcClientRepository.findByDni(dto.getDni()).isPresent()){
                throw new OperationException(FormatUtil.yaRegistrado("Cliente", "Dni", dto.getDni()));
            }
        }
    }
}
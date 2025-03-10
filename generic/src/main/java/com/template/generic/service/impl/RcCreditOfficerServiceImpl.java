package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.AmBranch;
import com.template.generic.model.MuUser;
import com.template.generic.model.RcClient;
import com.template.generic.model.RcCreditOfficer;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcCreditOfficerDto;
import com.template.generic.repository.AmBranchRepository;
import com.template.generic.repository.MuUserRepository;
import com.template.generic.repository.RcCreditOfficerRepository;
import com.template.generic.service.AmBranchService;
import com.template.generic.service.RcCreditOfficerService;
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
public class RcCreditOfficerServiceImpl implements RcCreditOfficerService {
    @Autowired
    private RcCreditOfficerRepository rcCreditOfficerRepository;
    @Autowired
    private AmBranchRepository amBranchRepository;
    @Autowired
    private MuUserRepository muUserRepository;



    @Override
    @Transactional(readOnly = true)
    public Page<RcCreditOfficerDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.rcCreditOfficerRepository.pageWithFilter(filter, pageable, RcCreditOfficerDto.class);
    }

    @Override
    public RcCreditOfficerDto findById(Long id) {
        RcCreditOfficer rcCreditOfficer = this.rcCreditOfficerRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Oficial de Crédito con id: "+id));
        return RcCreditOfficerDto.getDto(rcCreditOfficer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcCreditOfficerDto create(RcCreditOfficerDto rcCreditOfficerDto) {
        this.validate(rcCreditOfficerDto, true);
        RcCreditOfficer rcCreditOfficer = rcCreditOfficerDto.getEntity();

        AmBranch amBranch = this.amBranchRepository.findById(rcCreditOfficerDto.getIdBranch())
                .orElseThrow(()->new OperationException("No existe un Sucursal con id: "+rcCreditOfficerDto.getIdBranch()));
        rcCreditOfficer.setBranch(amBranch);

        if (rcCreditOfficerDto.getIdUser() != null){
            MuUser muUser = this.muUserRepository.findById(rcCreditOfficerDto.getIdUser())
                    .orElseThrow(()->new OperationException("No existe un Usuario con id: "+rcCreditOfficerDto.getIdUser()));
            rcCreditOfficer.setUser(muUser);
        }

        this.rcCreditOfficerRepository.save(rcCreditOfficer);
        return RcCreditOfficerDto.getDto(rcCreditOfficer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RcCreditOfficerDto update(RcCreditOfficerDto rcCreditOfficerDto) {
        this.validate(rcCreditOfficerDto, false);
        RcCreditOfficer rcCreditOfficer = this.rcCreditOfficerRepository.findById(rcCreditOfficerDto.getId())
                .orElseThrow(()->new OperationException("No existe un Oficial de Crédito con id: "+rcCreditOfficerDto.getId()));

        rcCreditOfficer.setDni(rcCreditOfficerDto.getDni());
        rcCreditOfficer.setNames(rcCreditOfficerDto.getNames());
        rcCreditOfficer.setLastnames(rcCreditOfficerDto.getLastnames());
        rcCreditOfficer.setPhone(rcCreditOfficerDto.getPhone());
        rcCreditOfficer.setEmail(rcCreditOfficerDto.getEmail());
        rcCreditOfficer.setAddress(rcCreditOfficerDto.getAddress());

        AmBranch amBranch = this.amBranchRepository.findById(rcCreditOfficerDto.getIdBranch())
                .orElseThrow(()->new OperationException("No existe un Sucursal con id: "+rcCreditOfficerDto.getIdBranch()));
        rcCreditOfficer.setBranch(amBranch);

        if (rcCreditOfficerDto.getIdUser() != null){
            MuUser muUser = this.muUserRepository.findById(rcCreditOfficerDto.getIdUser())
                    .orElseThrow(()->new OperationException("No existe un Usuario con id: "+rcCreditOfficerDto.getIdUser()));
            rcCreditOfficer.setUser(muUser);
        }

        this.rcCreditOfficerRepository.save(rcCreditOfficer);
        return RcCreditOfficerDto.getDto(rcCreditOfficer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        RcCreditOfficer rcCreditOfficer = this.rcCreditOfficerRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Oficial de Crédito con id: "+id));
        rcCreditOfficer.setDeleted(true);
        this.rcCreditOfficerRepository.save(rcCreditOfficer);
    }

    private void validate(RcCreditOfficerDto dto, boolean validDni){
        if (dto == null){
            throw new OperationException("El cliente recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionRequiredIfBlank("Dni", dto.getDni());
            ValidationUtil.throwExceptionRequiredIfBlank("Nombre", dto.getNames());
            ValidationUtil.throwExceptionRequiredIfBlank("Apellidos", dto.getLastnames());
            ValidationUtil.throwExceptionRequiredIfBlank("Telefono", dto.getPhone());
            ValidationUtil.throwExceptionRequiredIfBlank("Email", dto.getEmail());
            ValidationUtil.throwExceptionRequiredIfBlank("Dirección", dto.getAddress());

            ValidationUtil.throwExceptionRequiredIfNull("Sucursal de Oficial de Crédito", dto.getIdBranch());


            if (validDni && this.rcCreditOfficerRepository.findByDni(dto.getDni()).isPresent()){
                throw new OperationException(FormatUtil.yaRegistrado("Oficial de Crédito", "Dni", dto.getDni()));
            }
        }
    }
}

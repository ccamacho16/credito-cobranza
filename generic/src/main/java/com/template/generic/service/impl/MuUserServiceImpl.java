package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.MuUser;
import com.template.generic.model.dto.MuUserDto;
import com.template.generic.repository.AmBranchRepository;
import com.template.generic.repository.MuRoleRepository;
import com.template.generic.repository.MuUserRepository;
import com.template.generic.service.MuUserService;
import com.template.generic.util.FormatUtil;
import com.template.generic.util.StringUtil;
import com.template.generic.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class MuUserServiceImpl implements MuUserService {

    @Autowired
    private MuUserRepository muUserRepository;

    @Autowired
    private MuRoleRepository muRoleRepository;

    @Autowired
    private AmBranchRepository amBranchRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MuUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MuUserDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.muUserRepository.pageWithFilter(filter, pageable, MuUserDto.class);
    }

    @Override
    public MuUserDto findById(Long id) {
        MuUser muUser = this.muUserRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Usuario con id: "+id));
        return MuUserDto.getDto(muUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MuUserDto create(MuUserDto muUserDto) {
        this.validate(muUserDto, true);
        MuUser muUser = muUserDto.getEntity();

        muUser.setRole(this.muRoleRepository.findById(muUserDto.getIdRole()).
                orElseThrow(()->new OperationException("No existe un Rol con id: "+muUserDto.getIdRole())));

        muUser.setBranch(this.amBranchRepository.findById(muUserDto.getIdBranch()).
                orElseThrow(()->new OperationException("No existe un Sucursal con id: "+muUserDto.getIdBranch())));

        muUser.setPassword(passwordEncoder.encode(muUserDto.getPassword()));
        System.out.println("passsworddd");
        System.out.println(muUser.getPassword());

        this.muUserRepository.save(muUser);
        return MuUserDto.getDto(muUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MuUserDto update(MuUserDto muUserDto) {
        this.validate(muUserDto, false);

        MuUser muUser = this.muUserRepository.findById(muUserDto.getId())
                .orElseThrow(()->new OperationException("No existe una Sucursal con id: "+muUserDto.getId()));

        muUser.setName(muUserDto.getName());
        muUser.setUsername(muUserDto.getUsername());
        muUser.setEmail(muUserDto.getEmail());
        muUser.setStatus(muUserDto.getStatus());

        if (muUserDto.getPassword() != null && !muUserDto.getPassword().isEmpty()) {
            muUser.setPassword(passwordEncoder.encode(muUserDto.getPassword())); // Encriptar solo si cambia
        }

        muUser.setRole(this.muRoleRepository.findById(muUserDto.getIdRole()).
                orElseThrow(()->new OperationException("No existe un Rol con id: "+muUserDto.getIdRole())));

        muUser.setBranch(this.amBranchRepository.findById(muUserDto.getIdBranch()).
                orElseThrow(()->new OperationException("No existe un Sucursal con id: "+muUserDto.getIdBranch())));

        this.muUserRepository.save(muUser);
        return MuUserDto.getDto(muUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        MuUser muUser = this.muUserRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Usuario con id: "+id));
        muUser.setDeleted(true);
        this.muUserRepository.save(muUser);
    }

    private void validate(MuUserDto dto, boolean validUsername){
        if (dto == null){
            throw new OperationException("El Usuario recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionRequiredIfBlank("Nombre", dto.getName());
            ValidationUtil.throwExceptionRequiredIfBlank("Usuario", dto.getUsername());
            if (validUsername) {
                ValidationUtil.throwExceptionRequiredIfBlank("Contraseña", dto.getPassword());
            }
            ValidationUtil.throwExceptionRequiredIfBlank("Email", dto.getEmail());
            ValidationUtil.throwExceptionRequiredIfBlank("Estatus", dto.getStatus().toString());

            if (validUsername && this.muUserRepository.findByUsername(dto.getUsername()).isPresent()){
                throw new OperationException(FormatUtil.yaRegistrado("Usuario", "username", dto.getUsername()));
            }
        }
    }
}

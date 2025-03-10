package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.MuMenu;
import com.template.generic.model.MuRole;
import com.template.generic.model.MuRoleMenu;
import com.template.generic.model.MuUser;
import com.template.generic.model.dto.MuUserDto;
import com.template.generic.model.dto.select.MenuSelectDto;
import com.template.generic.repository.MuMenuRepository;
import com.template.generic.repository.MuRoleMenuRepository;
import com.template.generic.repository.MuRoleRepository;
import com.template.generic.service.MuRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
 * 19.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class MuRolServiceImpl implements MuRolService {

    @Autowired
    private MuRoleRepository muRoleRepository;

    @Autowired
    private MuMenuRepository muMenuRepository;

    @Autowired
    private MuRoleMenuRepository muRoleMenuRepository;

    @Override
    public List<MenuSelectDto> getListMenuBasic(Long idRol) {
        List<MenuSelectDto> listAllMenus = this.muMenuRepository.getListAllBasicData();

        List<Long> assignedMenuIds = this.muRoleMenuRepository.listAllByRole(idRol).
                stream().map(rm -> rm.getMenu().getId()).toList();

        return listAllMenus.stream()
                .peek(menu -> menu.setSelect(assignedMenuIds.contains(menu.getId())))
                .toList();

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveListMenusRol(List<Long> idMenus, Long idRole) {

        MuRole role = this.muRoleRepository.findById(idRole)
                .orElseThrow(()->new OperationException("No existe un Rol con id: "+idRole));

        this.muRoleMenuRepository.deleteByRole(role);

        for (Long idMenu: idMenus){

            MuMenu menu = this.muMenuRepository.findById(idMenu)
                    .orElseThrow(()->new OperationException("No existe un Menu con id: "+idMenu));

            this.muRoleMenuRepository.save(MuRoleMenu.builder()
                                                        .role(role)
                                                        .menu(menu).build());
        }
    }
}

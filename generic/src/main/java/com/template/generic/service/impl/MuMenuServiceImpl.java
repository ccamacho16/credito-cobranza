package com.template.generic.service.impl;

import com.template.generic.model.MuMenu;
import com.template.generic.model.dto.MuMenuDto;
import com.template.generic.repository.MuMenuRepository;
import com.template.generic.service.MuMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class MuMenuServiceImpl implements MuMenuService {

    @Autowired
    private MuMenuRepository muMenuRepository;


    @Override
    public List<MuMenuDto> getListMenuUser(String username) {
        List<MuMenuDto> listChildren = null;
        List<MuMenuDto> listMenu = null;
        if (username != null && !username.isEmpty()){
            listMenu = this.muMenuRepository.getListMenuUserFather(username);
            for (MuMenuDto optionFather: listMenu){
                listChildren = this.muMenuRepository.getListMenuUserChildren(username, optionFather.getId());
                optionFather.setChildrenDto(listChildren);
            }
        }
        return listMenu;
    }

    @Override
    public String getTextAccess(String url) {
        String text = "";
        Optional<MuMenu> menuOptional = this.muMenuRepository.findByUrl(url);
        if (menuOptional.isPresent()){
            MuMenu menu = menuOptional.get();
            text = "/" + menu.getFather().getName() + "/" + menu.getName();
        }
        System.out.println(text);
        return text;
    }
}

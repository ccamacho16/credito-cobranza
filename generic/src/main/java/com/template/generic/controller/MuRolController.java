package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.MuRoleMenu;
import com.template.generic.repository.MuRoleMenuRepository;
import com.template.generic.repository.MuRoleRepository;
import com.template.generic.service.MuRolService;
import com.template.generic.util.Constants;
import com.template.generic.util.CustomErrorType;
import com.template.generic.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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
 * 18.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/rol")
public class MuRolController {

    @Autowired
    private MuRoleRepository muRoleRepository;
    @Autowired
    private MuRoleMenuRepository muRoleMenuRepository;
    @Autowired
    private MuRolService muRolService;

    @RequestMapping(value = "/list_for_selection", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listForSelection() {
        try {
            return ok(muRoleRepository.listForSelection());
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Rol. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Rol", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Rol.", e);
            return CustomErrorType.SERVER_ERROR("Listar Rol", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/list_menu_basic/{idRole}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listMenuBasic(@PathVariable("idRole") Long idRole) {
        try {
            return ok(muRolService.getListMenuBasic(idRole));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Menu Basico. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Menu Basico", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al Menu Basico.", e);
            return CustomErrorType.SERVER_ERROR("Menu Basico", Constants.Message.ERROR_INTERNO);
        }
    }


    @RequestMapping(value = "/list_name_menu_by_role/{nameRole}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listNameMenuByRole(@PathVariable("nameRole") String nameRole) {
        try {
            return ok(muRoleMenuRepository.listNameMenuByRole(nameRole));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar nombre menu por rol. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("nombre menu por rol", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al nombre menu por rol.", e);
            return CustomErrorType.SERVER_ERROR("nombre menu por rol", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save-list-menus-rol", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity saveListMenusRol(@RequestBody List<Long> idListMenus, @RequestParam Long idRole) {
        try {
            this.muRolService.saveListMenusRol(idListMenus, idRole);
            return ResponseEntity.ok().build();
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al guardar los números. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al guardar los números.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Números", Constants.Message.ERROR_INTERNO);
        }
    }
}

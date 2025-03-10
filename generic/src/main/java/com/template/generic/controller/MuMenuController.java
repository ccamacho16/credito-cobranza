package com.template.generic.controller;

import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.MuMenuDto;
import com.template.generic.service.MuMenuService;

import com.template.generic.util.CustomErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/menu")
public class MuMenuController {
    @Autowired
    private MuMenuService muMenuService;

    @GetMapping(value = "/list-menus-by-user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listar(@PathVariable("username") String username) {
        try {
            List<MuMenuDto> list = this.muMenuService.getListMenuUser(username);
            log.info("Listando Menus de usuario.");
            return ok(list);
        } catch (OperationException e) {
            log.error("Error al Listar Menus de Usuario. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Menus de Usuario", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Menus de Usuario.", e);
            return CustomErrorType.SERVER_ERROR("Listar menus de usuario", "Ocurrió un error interno");
        }
    }

    @GetMapping(value = "/text-access")
    public ResponseEntity<String> textAccess(@RequestParam("url") String url) {
        try {
            return ok(this.muMenuService.getTextAccess(url));
        } catch (OperationException e) {
            log.error("Error al obtener resultado textAccess. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener textAccess", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener resultado textAccess.", e);
            return CustomErrorType.SERVER_ERROR("Obtener textAccess", "Ocurrió un error interno");
        }
    }


}

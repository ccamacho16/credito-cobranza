package com.template.generic.controller;

import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.MuDomainValueDto;
import com.template.generic.model.dto.MuParameterDto;
import com.template.generic.model.dto.MuParameterGroupDto;
import com.template.generic.service.MuParameterGroupService;
import com.template.generic.service.MuParameterService;
import com.template.generic.util.CustomErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/parameter-group")
public class MuParameterGroupController {

    private final MuParameterGroupService muParameterGroupService;
    private final MuParameterService muParameterService;

    public MuParameterGroupController(MuParameterGroupService muParameterGroupService,
                                      MuParameterService muParameterService){
        this.muParameterGroupService = muParameterGroupService;
        this.muParameterService = muParameterService;
    }

    @GetMapping(value = "/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarAll() {
        try {
            List<MuParameterGroupDto> list = this.muParameterGroupService.getParameterGroupList();
            log.info("Listando grupos de parametros");
            return ok(list);
        } catch (OperationException e) {
            log.error("Error al Listar grupos de parametros. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar grupos de parametros", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar grupos de parametros", e);
            return CustomErrorType.SERVER_ERROR("Listar grupos de parametros", "Ocurrió un error interno");
        }
    }

    @GetMapping(value = "/list-parameter-by-group", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listParameterByGroup(@Param("nameGroup") String nameGroup) {
        try {
            List<MuParameterDto> list = this.muParameterService.listParametersByNameGroup(nameGroup);
            log.info("Listando parametros por grupo.");
            return ok(list);
        } catch (OperationException e) {
            log.error("Error al Listar parametros por grupo. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar parametros por grupo", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar parametros por grupo", e);
            return CustomErrorType.SERVER_ERROR("Listar parametros por grupo", "Ocurrió un error interno");
        }
    }

    @GetMapping(value = "/list-parameter-by-code-and-group", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listParameterByGroupAndCode(@Param("nameGroup") String nameGroup, @Param("code") String code) {
        try {
            MuParameterDto muParameterDto = this.muParameterService.listParameterByGroupAndCode(nameGroup, code);
            log.info("Listando parametros por grupo y code parametro.");
            return ok(muParameterDto);
        } catch (OperationException e) {
            log.error("Error al Listar parametros por grupo y code parametro. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar parametros por grupo y code parametro.", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar parametros por grupo y code parametro.", e);
            return CustomErrorType.SERVER_ERROR("Listar parametros por grupo y code parametro.", "Ocurrió un error interno");
        }
    }
}

package com.template.generic.controller;

import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.MuDomainValueDto;
import com.template.generic.model.dto.MuMenuDto;
import com.template.generic.service.MuDomainService;
import com.template.generic.util.CustomErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/domain")
public class MuDomainController {
    @Autowired
    private MuDomainService muDomainService;

    @GetMapping(value = "/list-value-by-domain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listar(@Param("idDomain") String idDomain) {
        try {
            List<MuDomainValueDto> list = this.muDomainService.findMuDomainValueByDomain(idDomain);
            log.info("Listando valores por Domain.");
            return ok(list);
        } catch (OperationException e) {
            log.error("Error al Listar valores por Domain. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar valores por Domain", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Menus de Usuario.", e);
            return CustomErrorType.SERVER_ERROR("Listar valores por Domain", "Ocurrió un error interno");
        }
    }
}

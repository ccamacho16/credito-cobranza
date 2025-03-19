package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.NgCreditDto;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.service.NgCreditService;
import com.template.generic.util.ApiUtil;
import com.template.generic.util.Constants;
import com.template.generic.util.CustomErrorType;
import com.template.generic.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * 12.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/credit")
public class NgCreditController {
    @Autowired
    private NgCreditService ngCreditService;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                       @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(ngCreditService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Créditos. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Créditos", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Créditos.", e);
            return CustomErrorType.SERVER_ERROR("Listar Créditos", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/find_by_id/{idCredit}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idCredit") Long idCredit) {
        try {
            return ok(this.ngCreditService.findById(idCredit));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener el Crédito. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Crédito", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener un Crédito.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody NgCreditDto dto) {
        try {
            return ok(this.ngCreditService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos del Crédito. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Crédito.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/list_payment_plan", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listPaymentPlan(@RequestBody NgCreditDto ngCreditDto) {
        try {
            return ok(ngCreditService.generatePaymentPlan(ngCreditDto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Plan de Pago. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Plan de Pago", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Plan de Pago.", e);
            return CustomErrorType.SERVER_ERROR("Listar Plan de Pago", Constants.Message.ERROR_INTERNO);
        }
    }
}

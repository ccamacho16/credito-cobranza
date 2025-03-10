package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.RcCreditOfficer;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcCreditOfficerDto;
import com.template.generic.service.RcCreditOfficerService;
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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/credit-officer")
public class RcCreditOfficerController {
    @Autowired
    private RcCreditOfficerService rcCreditOfficerService;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                       @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(rcCreditOfficerService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Oficiales de Crédito. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Oficiales de Crédito", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Oficiales de Crédito.", e);
            return CustomErrorType.SERVER_ERROR("Listar Oficiales de Crédito", Constants.Message.ERROR_INTERNO);
        }
    }
    @RequestMapping(value = "/find_by_id/{idCreditOfficer}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idCreditOfficer") Long idCreditOfficer) {
        try {
            return ok(this.rcCreditOfficerService.findById(idCreditOfficer));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener el Oficial de Crédito. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Oficial de Crédito", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener un Oficial de Crédito.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Oficial de Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody RcCreditOfficerDto dto) {
        try {
            return ok(this.rcCreditOfficerService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos del Oficial de Crédito. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Oficial de Crédito.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Oficial de Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody RcCreditOfficerDto dto) {
        try {
            return ok(this.rcCreditOfficerService.update(dto));
        } catch (OperationException | NotDataFoundException e ){
            log.error("Error al Actualizar Oficial de Crédito. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Actualizar Oficial de Crédito", e.getMessage());
        } catch (Exception e){
            log.error("Error no controlado al Actualizar Oficial de Crédito", e);
            return CustomErrorType.SERVER_ERROR("Actualizar Oficial de Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/delete/{idCreditOfficer}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idCreditOfficer") Long idCreditOfficer) {
        try {
            this.rcCreditOfficerService.delete(idCreditOfficer);
            return ok().build();
        } catch (OperationException e){
            log.error("Error al eliminar un Oficial de Crédito. Causa. {}",  e.getMessage());
            return CustomErrorType.BAD_REQUEST("Eliminar Oficial de Crédito", e.getMessage());
        }catch (Exception e) {
            log.error("Error no controlado al eliminar Oficial de Crédito.",  e);
            return CustomErrorType.SERVER_ERROR("Eliminar Oficial de Crédito", Constants.Message.ERROR_INTERNO);
        }
    }

}

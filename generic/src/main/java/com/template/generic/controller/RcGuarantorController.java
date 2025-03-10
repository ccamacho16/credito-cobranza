package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.RcGuarantor;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.RcGuarantorDto;
import com.template.generic.service.RcGuarantorService;
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
@RequestMapping("/guarantor")
public class RcGuarantorController {
    @Autowired
    private RcGuarantorService rcGuarantorService;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                       @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(rcGuarantorService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Garante. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Garante", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Garante.", e);
            return CustomErrorType.SERVER_ERROR("Listar Garante", Constants.Message.ERROR_INTERNO);
        }
    }
    @RequestMapping(value = "/find_by_id/{idGuarantor}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idGuarantor") Long idGuarantor) {
        try {
            return ok(this.rcGuarantorService.findById(idGuarantor));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener el Garante. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Garante", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener un Garante.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Garante", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody RcGuarantorDto dto) {
        try {
            return ok(this.rcGuarantorService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos del Garante. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Garante.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Garante", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody RcGuarantorDto dto) {
        try {
            return ok(this.rcGuarantorService.update(dto));
        } catch (OperationException | NotDataFoundException e ){
            log.error("Error al Actualizar Garante. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Actualizar Garante", e.getMessage());
        } catch (Exception e){
            log.error("Error no controlado al Actualizar Garante", e);
            return CustomErrorType.SERVER_ERROR("Actualizar Garante", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/delete/{idGuarantor}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idGuarantor") Long idGuarantor) {
        try {
            this.rcGuarantorService.delete(idGuarantor);
            return ok().build();
        } catch (OperationException e){
            log.error("Error al eliminar un Garante. Causa. {}",  e.getMessage());
            return CustomErrorType.BAD_REQUEST("Eliminar Garante", e.getMessage());
        }catch (Exception e) {
            log.error("Error no controlado al eliminar Garante.",  e);
            return CustomErrorType.SERVER_ERROR("Eliminar Garante", Constants.Message.ERROR_INTERNO);
        }
    }
}

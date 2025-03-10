package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.service.RcClientService;
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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class RcClientController {

    @Autowired
    private RcClientService rcClientService;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                          @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                          @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                          @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(rcClientService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Cliente. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Cliente", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Cliente.", e);
            return CustomErrorType.SERVER_ERROR("Listar Cliente", Constants.Message.ERROR_INTERNO);
        }
    }
    @RequestMapping(value = "/find_by_id/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idClient") Long idClient) {
        try {
            return ok(this.rcClientService.findById(idClient));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener el Cliente. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Cliente", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener un Cliente.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Cliente", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody RcClientDto dto) {
        try {
            return ok(this.rcClientService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos del Cliente. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Cliente.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Cliente", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody RcClientDto dto) {
        try {
            return ok(this.rcClientService.update(dto));
        } catch (OperationException | NotDataFoundException e ){
            log.error("Error al Actualizar Cliente. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Actualizar Cliente", e.getMessage());
        } catch (Exception e){
            log.error("Error no controlado al Actualizar Cliente", e);
            return CustomErrorType.SERVER_ERROR("Actualizar Cliente", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/delete/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idClient") Long idClient) {
        try {
            this.rcClientService.delete(idClient);
            return ok().build();
        } catch (OperationException e){
            log.error("Error al eliminar un Cliente. Causa. {}",  e.getMessage());
            return CustomErrorType.BAD_REQUEST("Eliminar Cliente", e.getMessage());
        }catch (Exception e) {
            log.error("Error no controlado al eliminar Cliente.",  e);
            return CustomErrorType.SERVER_ERROR("Eliminar Cliente", Constants.Message.ERROR_INTERNO);
        }
    }


}

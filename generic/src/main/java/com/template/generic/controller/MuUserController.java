package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.dto.MuUserDto;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.service.MuUserService;
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
 * 18.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class MuUserController {

    @Autowired
    private MuUserService muUserService;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                       @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(muUserService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Usuario. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Usuario", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Usuario.", e);
            return CustomErrorType.SERVER_ERROR("Listar Usuario", Constants.Message.ERROR_INTERNO);
        }
    }
    @RequestMapping(value = "/find_by_id/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idUser") Long idUser) {
        try {
            return ok(this.muUserService.findById(idUser));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener el Usuario. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Usuario", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener un Usuario.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Usuario", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MuUserDto dto) {
        try {
            return ok(this.muUserService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos del Usuario. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Usuario.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Usuario", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody MuUserDto dto) {
        try {
            return ok(this.muUserService.update(dto));
        } catch (OperationException | NotDataFoundException e ){
            log.error("Error al Actualizar Usuario. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Actualizar Usuario", e.getMessage());
        } catch (Exception e){
            log.error("Error no controlado al Actualizar Usuario", e);
            return CustomErrorType.SERVER_ERROR("Actualizar Usuario", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/delete/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idUser") Long idUser) {
        try {
            this.muUserService.delete(idUser);
            return ok().build();
        } catch (OperationException e){
            log.error("Error al eliminar un Usuario. Causa. {}",  e.getMessage());
            return CustomErrorType.BAD_REQUEST("Eliminar Usuario", e.getMessage());
        }catch (Exception e) {
            log.error("Error no controlado al eliminar Usuario.",  e);
            return CustomErrorType.SERVER_ERROR("Eliminar Usuario", Constants.Message.ERROR_INTERNO);
        }
    }
}

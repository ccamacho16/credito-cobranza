package com.template.generic.controller;

import com.template.generic.exception.NotDataFoundException;
import com.template.generic.exception.OperationException;
import com.template.generic.model.AmBranch;
import com.template.generic.model.dto.AmBranchDto;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.repository.AmBranchRepository;
import com.template.generic.service.AmBranchService;
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
 * 17.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/branch")
public class AmBranchController {
    @Autowired
    private AmBranchService amBranchService;

    @Autowired
    private AmBranchRepository amBranchRepository;

    @RequestMapping(value = "/list_by_filter", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listByFilter(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,
                                       @RequestParam String filter) {
        try {
            Pageable pagingSort = ApiUtil.buildPageableWithSort(page, size, sortBy, sortDir);
            return ok(amBranchService.listByFilter(filter, pagingSort));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Sucursal. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Sucursal", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Sucursal.", e);
            return CustomErrorType.SERVER_ERROR("Listar Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/list_for_selection", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity listForSelection() {
        try {
            return ok(amBranchRepository.listForSelection());
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al Listar Sucursal. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Listar Sucursal", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al listar Sucursal.", e);
            return CustomErrorType.SERVER_ERROR("Listar Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/find_by_id/{idBranch}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("idBranch") Long idBranch) {
        try {
            return ok(this.amBranchService.findById(idBranch));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error al obtener la Sucursal. Causa: {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Obtener Sucursal", e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al obtener una Sucursal.", e);
            return CustomErrorType.SERVER_ERROR("Obtener Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody AmBranchDto dto) {
        try {
            return ok(this.amBranchService.create(dto));
        } catch (OperationException | NotDataFoundException e) {
            log.error("Error no controlado al persistir los datos de la Sucursal. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST(FormatUtil.MSG_TITLE_ERROR, e.getMessage());
        } catch (Exception e) {
            log.error("Error no controlado al persistir los datos de Sucursal.", e);
            return CustomErrorType.SERVER_ERROR("Guardar Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody AmBranchDto dto) {
        try {
            return ok(this.amBranchService.update(dto));
        } catch (OperationException | NotDataFoundException e ){
            log.error("Error al Actualizar Sucursal. Causa. {}", e.getMessage());
            return CustomErrorType.BAD_REQUEST("Actualizar Sucursal", e.getMessage());
        } catch (Exception e){
            log.error("Error no controlado al Actualizar Sucursal", e);
            return CustomErrorType.SERVER_ERROR("Actualizar Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }

    @RequestMapping(value = "/delete/{idBranch}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idBranch") Long idBranch) {
        try {
            this.amBranchService.delete(idBranch);
            return ok().build();
        } catch (OperationException e){
            log.error("Error al eliminar una Sucursal. Causa. {}",  e.getMessage());
            return CustomErrorType.BAD_REQUEST("Eliminar Sucursal", e.getMessage());
        }catch (Exception e) {
            log.error("Error no controlado al eliminar Sucursal.",  e);
            return CustomErrorType.SERVER_ERROR("Eliminar Sucursal", Constants.Message.ERROR_INTERNO);
        }
    }
}

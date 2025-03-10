package com.template.generic.repository;

import com.template.generic.model.MuRole;
import com.template.generic.model.dto.select.CodeDescriptionDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Repository
public interface MuRoleRepository extends CrudRepository<MuRole, Long> {
    Optional<MuRole> findByName(String name);

    @Query("SELECT new com.template.generic.model.dto.select.CodeDescriptionDto(r.id, r.name) " +
           "FROM MuRole r " +
           "WHERE r.deleted = FALSE " +
           "ORDER BY r.name ASC")
    List<CodeDescriptionDto> listForSelection();


}

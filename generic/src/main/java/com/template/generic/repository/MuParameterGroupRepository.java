package com.template.generic.repository;

import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.dto.MuParameterGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuParameterGroupRepository extends JpaRepository<MuParameterGroup, Long> {
    @Query( "   SELECT new com.template.generic.model.dto.MuParameterGroupDto(gp) " +
            "   FROM MuParameterGroup gp " +
            "   WHERE gp.deleted = false ")
    List<MuParameterGroupDto> listaGruposParametro();

    @Query( "   SELECT gp " +
            "   FROM MuParameterGroup gp " +
            "   WHERE gp.deleted = false " +
            "    AND gp.groupName=:grupo")
    Optional<MuParameterGroup> findByGrupo(@Param("grupo") String grupo);


}

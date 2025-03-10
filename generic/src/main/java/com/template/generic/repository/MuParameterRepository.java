package com.template.generic.repository;

import com.template.generic.model.MuParameter;
import com.template.generic.model.MuParameterGroup;
import com.template.generic.model.dto.MuParameterDto;
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
public interface MuParameterRepository extends JpaRepository<MuParameter, Long> {
    @Query("    SELECT par " +
            "   FROM MuParameter par " +
            "   WHERE par.deleted = false " +
            "   AND TRIM(UPPER(par.code)) = TRIM(UPPER(:codigo)) ")
    Optional<MuParameter> findParametroByCodigo(@Param("codigo") String codigo);

    @Query( " SELECT new com.template.generic.model.dto.MuParameterDto(par) " +
            " FROM MuParameter par " +
            " WHERE par.deleted = FALSE " +
            " AND par.muParameterGroup = :parameterGroup " +
            " ORDER BY par.code ASC")
    List<MuParameterDto> getParametersByNameGroup(@Param("parameterGroup") MuParameterGroup parameterGroup);

    @Query( " SELECT new com.template.generic.model.dto.MuParameterDto(par) " +
            " FROM MuParameter par " +
            " WHERE par.deleted = FALSE " +
            " AND par.muParameterGroup = :parameterGroup " +
            " AND par.code = :code" +
            " ORDER BY par.code ASC")
    MuParameterDto getParametersByNameGroupAndCode(@Param("parameterGroup") MuParameterGroup parameterGroup,
                                                         @Param("code") String code);
}

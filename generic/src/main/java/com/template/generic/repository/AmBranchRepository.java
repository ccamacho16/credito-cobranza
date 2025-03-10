package com.template.generic.repository;

import com.template.generic.model.AmBranch;
import com.template.generic.model.dto.select.CodeDescriptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
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
 * 28.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface AmBranchRepository extends JpaRepository<AmBranch, Long> {

    @Query("SELECT new com.template.generic.model.dto.AmBranchDto(b) " +
            "FROM AmBranch b " +
            "WHERE b.deleted = FALSE " +
            "  AND (:filter IS NULL OR UPPER(b.name) LIKE :filter " +
            "   OR UPPER(b.description) LIKE :filter " +
            "   OR UPPER(b.address) LIKE :filter " +
            "   OR UPPER(b.phone) LIKE :filter )")
    <T> Page<T> pageWithFilter(@Param("filter") String filter,
                               Pageable pageable, Class<T> type);
    Optional<AmBranch> findByName(String name);

    @Query("SELECT new com.template.generic.model.dto.select.CodeDescriptionDto(b.id, b.name) " +
            "FROM AmBranch b " +
            "WHERE b.deleted = FALSE " +
            "ORDER BY b.name ASC")
    List<CodeDescriptionDto> listForSelection();
}

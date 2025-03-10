package com.template.generic.repository;

import com.template.generic.model.MuUser;
import com.template.generic.model.dto.select.CodeDescriptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface MuUserRepository extends CrudRepository<MuUser, Long> {

    @Query("SELECT new com.template.generic.model.dto.MuUserDto(u) " +
            "FROM MuUser u " +
            "WHERE u.deleted = FALSE " +
            "  AND (:filter IS NULL OR UPPER(u.name) LIKE :filter " +
            "   OR UPPER(u.username) LIKE :filter )")
    <T> Page<T> pageWithFilter(@Param("filter") String filter,
                               Pageable pageable, Class<T> type);
    @Query("SELECT u " +
           "FROM MuUser u " +
           "WHERE u.username = :username AND u.status = 'ACTIVO' ")
    Optional<MuUser> findByUsername(@Param("username") String username);


}

package com.template.generic.repository;

import com.template.generic.model.MuMenu;
import com.template.generic.model.dto.MuMenuDto;
import com.template.generic.model.dto.select.MenuSelectDto;
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
 * 29.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuMenuRepository extends JpaRepository<MuMenu, Long> {
    Optional<MuMenu> findByName(String name);

    Optional<MuMenu> findByUrl(String url);

    @Query("SELECT new com.template.generic.model.dto.MuMenuDto(m) " +
            "FROM MuUser u " +
            "LEFT JOIN MuRole r ON u.role.id = r.id " +
            "LEFT JOIN MuRoleMenu rm ON r.id = rm.role.id " +
            "LEFT JOIN MuMenu m ON rm.menu.id = m.id " +
            "WHERE u.deleted = FALSE and m.father IS NULL AND u.username = :username " +
            " ORDER BY COALESCE(m.father.id, m.id), CASE WHEN m.father.id IS NULL THEN 0 ELSE 1 END, m.sequence")
    List<MuMenuDto> getListMenuUserFather(@Param("username") String username);

    @Query("SELECT new com.template.generic.model.dto.MuMenuDto(m) " +
            "FROM MuUser u " +
            "LEFT JOIN MuRole r ON u.role.id = r.id " +
            "LEFT JOIN MuRoleMenu rm ON r.id = rm.role.id " +
            "LEFT JOIN MuMenu m ON rm.menu.id = m.id " +
            "WHERE u.deleted = FALSE and m.father.id = :idFather AND u.username = :username " +
            " ORDER BY COALESCE(m.father.id, m.id), CASE WHEN m.father.id IS NULL THEN 0 ELSE 1 END, m.sequence")
    List<MuMenuDto> getListMenuUserChildren(@Param("username") String username, @Param("idFather") Long idFather);

    @Query("SELECT new com.template.generic.model.dto.select.MenuSelectDto(m) " +
           "FROM MuMenu m " +
           "WHERE m.deleted = FALSE " +
           "ORDER BY COALESCE(m.father.id, m.id), CASE WHEN m.father.id IS NULL THEN 0 ELSE 1 END, m.sequence ")
    List<MenuSelectDto> getListAllBasicData();


}

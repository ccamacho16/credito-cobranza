package com.template.generic.repository;

import com.template.generic.model.MuRole;
import com.template.generic.model.MuRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuRoleMenuRepository extends JpaRepository<MuRoleMenu, Long> {
    @Query(" SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            " FROM MuRoleMenu r" +
            " WHERE r.deleted = FALSE " +
            "   AND r.role.id = :roleId" +
            "   AND r.menu.id = :menuId")
    boolean existsByRoleAndMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    @Query("SELECT rm " +
           "FROM MuRoleMenu rm " +
           "WHERE rm.deleted = FALSE AND rm.role.id = :idRole ")
    List<MuRoleMenu> listAllByRole(@Param("idRole") Long idRole);

    @Query("SELECT rm.menu.url " +
            "FROM MuRoleMenu rm " +
            "WHERE rm.deleted = FALSE " +
            "  AND rm.role.name = :nameRole " +
            "  AND rm.menu.father IS NOT NULL")
    List<String> listNameMenuByRole(@Param("nameRole") String nameRole);
    void deleteByRole(MuRole role);
}

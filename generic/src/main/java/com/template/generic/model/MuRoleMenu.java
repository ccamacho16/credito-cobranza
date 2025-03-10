package com.template.generic.model;

import com.template.generic.model.AmBranch;
import com.template.generic.model.AuditableEntity;
import com.template.generic.model.MuMenu;
import com.template.generic.model.MuRole;
import jakarta.persistence.*;
import lombok.*;

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
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mu_role_menu")
public class MuRoleMenu  extends AuditableEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_ROLE_MENU_ID_GNRT", sequenceName = "SEQ_ROLE_MENU_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE_MENU_ID_GNRT")
    private Long id;

    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MuMenu menu;

    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MuRole role;
}

package com.template.generic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mu_menu")
public class MuMenu extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_MENU_ID_GNRT", sequenceName = "SEQ_MENU_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_ID_GNRT")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "icon", nullable = true)
    private String icon;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private MuMenu father;

    @OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
    private Set<MuMenu> children;

    @JsonIgnore
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private Set<MuRoleMenu> roleMenus;

}

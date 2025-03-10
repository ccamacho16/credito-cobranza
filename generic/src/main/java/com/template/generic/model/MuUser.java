package com.template.generic.model;

import com.template.generic.model.enums.UserStatusEnum;
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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mu_user")
public class MuUser extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_USER_ID_GNRT", sequenceName = "SEQ_USER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ID_GNRT")
    private Long id;

    @Column(name = "name", nullable = false,length = 255)
    private String name;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatusEnum status;



    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MuRole role;

    @JoinColumn(name = "id_branch", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AmBranch branch;
}

package com.template.generic.model;

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
 * 27.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rc_credit_officer")
public class RcCreditOfficer extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_CREDIT_OFI_ID_GNRT", sequenceName = "SEQ_CREDIT_OFI_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDIT_OFI_ID_GNRT")
    private Long id;

    @Column(name = "dni", length = 50, nullable = false, unique = true)
    private String dni;

    @Column(name = "names", length = 50, nullable = false)
    private String names;

    @Column(name = "lastnames", length = 100, nullable = false)
    private String lastnames;

    @Column(name = "phone", length = 50, nullable = false)
    private String phone;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "address", length = 150, nullable = false)
    private String address;

    @JoinColumn(name = "id_branch", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AmBranch branch;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private MuUser user;


}

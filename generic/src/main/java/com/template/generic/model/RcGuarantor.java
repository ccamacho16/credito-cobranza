package com.template.generic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
@Table(name = "rc_guarantor")
public class RcGuarantor extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_GUARANTOR_ID_GNRT", sequenceName = "SEQ_GUARANTOR_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GUARANTOR_ID_GNRT")
    private Long id;

    @Column(name = "dni", length = 50, nullable = false, unique = true)
    private String dni;

    @Column(name = "names", length = 50, nullable = false)
    private String names;

    @Column(name = "lastnames", length = 100, nullable = false)
    private String lastnames;

    @Column(name = "birthdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "civil_status", length = 50, nullable = false)
    private String civilStatus;  //CASADA - DIVORCIADA - ETC.

    @Column(name = "phone", length = 50, nullable = false)
    private String phone;

    @Column(name = "address_home", length = 150, nullable = false)
    private String addressHome;

    @Column(name = "address_business", length = 150)
    private String addressBusiness;

    @Column(name = "relationship", length = 100, nullable = false)
    private String relationship;  // relacion con el cliente.

}

package com.template.generic.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.security.PrivateKey;
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
 * 05.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rc_client")
public class RcClient extends AuditableEntity{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_CLIENT_ID_GNRT", sequenceName = "SEQ_CLIENT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENT_ID_GNRT")
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

    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Column(name = "civil_status", length = 50, nullable = false)
    private String civilStatus;  //CASADA - DIVORCIADA - ETC.

    @Column(name = "phone", length = 50, nullable = false)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "income_month", nullable = false)
    private Integer incomeMonth;

    @Column(name = "address_home", length = 150, nullable = false)
    private String addressHome;

    @Column(name = "address_business", length = 150)
    private String addressBusiness;

    @Column(name = "category", length = 50, nullable = false)
    private String category;  //FORMAL - INFORMAL

    @Column(name = "occupation", length = 50, nullable = false)
    private String occupation;  //COMIDA - ALBAÑIL - CARPINTERO - CHOFER - ETC....

    @Column(name = "comment", length = 500)
    private String comment;
}

package com.template.generic.repository;

import com.template.generic.model.AmBranch;
import com.template.generic.model.RcClient;
import com.template.generic.model.dto.MuMenuDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
/*@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)*/
class MuRoleRepositoryTest {
   /* @Autowired
    private RcClientRepository rcClientRepository;



    @Test
    @Commit
    public void findByDniClient(){
        RcClient c1 = RcClient.builder()
                .dni("6207168")
                .names("Crisvel")
                .lastnames("Camacho Orellana")
                .gender("Masculino")
                .phone("70905333")
                .category("Empresa").build();

        RcClient c2 = RcClient.builder()
                .dni("6207256")
                .names("Anthony")
                .lastnames("Camacho Castro")
                .gender("Masculino")
                .phone("70905111")
                .category("Empresa").build();

        this.rcClientRepository.save(c1);
        this.rcClientRepository.save(c2);

        RcClient c = this.rcClientRepository.findByDni("6207256").orElse(null);
        System.out.println(c);
    }*/
}
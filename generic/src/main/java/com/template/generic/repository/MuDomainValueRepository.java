package com.template.generic.repository;

import com.template.generic.model.MuDomain;
import com.template.generic.model.MuDomainValue;
import com.template.generic.model.dto.MuDomainValueDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
 * 04.02.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public interface MuDomainValueRepository extends JpaRepository<MuDomainValue, Long> {
    @Query( "SELECT new com.template.generic.model.dto.MuDomainValueDto(dv) " +
            "FROM MuDomainValue dv INNER JOIN dv.domain d " +
            "WHERE d.id =:domainId AND dv.deleted = FALSE " +
            "AND (upper(dv.value) like upper(:filter) " +
            "OR upper(dv.description) like upper(:filter))")
    Page<MuDomainValueDto> findDomainValueByDomain(@Param("domainId") String domain,
                                                   @Param("filter") String filter,
                                                   Pageable pageable);

    Optional<MuDomainValue> findMuDomainValuesByDeletedIsFalseAndValue(String value);

    @Query( "SELECT dv " +
            "FROM MuDomainValue dv " +
            "WHERE dv.deleted = FALSE " +
            " AND dv.domain = :domain "+
            " AND dv.value = :value ")
    Optional<MuDomainValue> findByValueAndDomain(@Param("value") String value, @Param("domain") MuDomain domain);

    Optional<MuDomainValue> findByDeletedFalseAndValue(String value);

    @Modifying
    @Query( "UPDATE MuDomainValue dv " +
            "SET dv.deleted = TRUE " +
            "WHERE dv = :domainValue")
    void deleteDomainValue(@Param("domainValue") MuDomainValue domainValue);

    @Query( "SELECT new com.template.generic.model.dto.MuDomainValueDto(dv) " +
            "FROM MuDomainValue dv INNER JOIN dv.domain d " +
            "WHERE d.id =:domainId AND dv.deleted = FALSE " )
    List<MuDomainValueDto> findDomainValueByDomain(@Param("domainId") String domain);
}

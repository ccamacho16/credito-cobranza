package com.template.generic;

import com.template.generic.model.*;
import com.template.generic.model.dto.MuDomainValueDto;
import com.template.generic.model.enums.ParameterTypeEnum;
import com.template.generic.model.enums.UserStatusEnum;
import com.template.generic.repository.*;
import com.template.generic.service.MuDomainService;
import com.template.generic.service.MuParameterGroupService;
import com.template.generic.service.MuParameterService;
import com.template.generic.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.Random;

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
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MuUserRepository muUserRepository;
    private final AmBranchRepository amBranchRepository;
    private final MuRoleRepository muRoleRepository;
    private final MuMenuRepository muMenuRepository;
    private final MuRoleMenuRepository muRoleMenuRepository;
    private final MuDomainService muDomainService;
    private final MuDomainRepository muDomainRepository;
    private final PasswordEncoder passwordEncoder;
    private final MuParameterService muParameterService;
    private final MuParameterGroupService muParameterGroupService;
    private final RcClientRepository rcClientRepository;

    private static final Faker faker = new Faker();
    private static final Random random = new Random();


    @Override
    public void run(String... args) throws Exception {
        this.configSecurity();
        this.buildDefaultMenu();
        this.addDomains();
        this.addParams();
        this.loadTestClients(50);
        //amBranchRepository.save(AmBranch.builder().name("Centra").address("").phone().build());
        /*
        configSecurity();  // administra los usurios y roles
        addParams();       // administra los parametros generales del sistema Ej. Pais, empresa, nit, razon social, etc.
        addDomains();      // administra los parametros funcionales del sistema Ej. Tipo sangre, Genero, Bancos, meses, etc
        addDataInitial();  // administra los parametros funcionales que tienen una estructura particular. Ej. [descripcion y valor]
        addActions();      // creo que guarda el usuario authenticado. [] revisar
        buildDefaultMenu(); // Administra la opciones que van a ir en el menu.
        */


    }

    private void configBaseSecurity(){
        AmBranch branch = this.amBranchRepository.findByName("Central").orElse(null);
        if (branch == null){
            branch = amBranchRepository.save(AmBranch.builder()
                                .name("Central")
                                .description("Central")
                                .address("Av. Principal #111")
                                .phone("+591 701-11111")
                                .build());
        }

        MuRole role = this.muRoleRepository.findByName("Master").orElse(null);
        if (role == null){
            role = muRoleRepository.save(MuRole.builder()
                    .name("Master")
                    .description("Master System").build());
        }

        MuUser user = this.muUserRepository.findByUsername("admin").orElse(null);
        if (user == null){
            muUserRepository.save(MuUser.builder()
                    .name("Administrador")
                    .username("admin")
                    .password(passwordEncoder.encode("12345"))
                    .email("admin@generic.com")
                    .status(UserStatusEnum.ACTIVO)
                    .branch(branch)
                    .role(role)
                    .build());
        }
    }
    private void configSecurity(){
        log.info("******************************* Cargado de Roles *******************************");

        this.configBaseSecurity();

        MuRole roleGerente = this.muRoleRepository.findByName("Gerente").orElse(null);
        if (roleGerente == null){
            muRoleRepository.save(MuRole.builder()
                    .name("Gerente")
                    .description("Gerencias")
                    .build());
        }

        /*MuRole roleSupervisor = this.muRoleRepository.findByName(RoleEnum.SUPERVISOR).orElse(null);
        if (roleSupervisor == null){
            muRoleRepository.save(MuRole.builder()
                    .name(RoleEnum.SUPERVISOR)
                    .description("Supervisor")
                    .build());
        }

        MuRole roleOperador = this.muRoleRepository.findByName(RoleEnum.OPERADOR).orElse(null);
        if (roleOperador == null){
            muRoleRepository.save(MuRole.builder()
                    .name(RoleEnum.OPERADOR)
                    .description("Operador")
                    .build());
        }

        MuRole roleInvitado = this.muRoleRepository.findByName(RoleEnum.INVITADO).orElse(null);
        if (roleInvitado == null){
            muRoleRepository.save(MuRole.builder()
                    .name(RoleEnum.INVITADO)
                    .description("Invitado")
                    .build());
        }

        MuRole roleCajero = this.muRoleRepository.findByName(RoleEnum.CAJERO).orElse(null);
        if (roleCajero == null){
            muRoleRepository.save(MuRole.builder()
                    .name(RoleEnum.CAJERO)
                    .description("Cajero")
                    .build());
        }*/

    }

    private void buildDefaultMenu(){

        log.info("******************************* Cargado de Menus/Menus - Roles *******************************");

        MuRole roleMaster = this.muRoleRepository.findByName("Master").orElse(null);

        if (roleMaster != null) {

            MuMenu optionAdministracion = this.createOrUpdateOptionMenu("Administración",
                    "administracion",
                    "icono_config",
                    2,
                    null);
            this.addOptionMenuToRole(optionAdministracion, roleMaster);

            MuMenu optionBranch = this.createOrUpdateOptionMenu("Sucursal",
                    "/sucursal",
                    "icono_link",
                    1,
                    optionAdministracion);
            this.addOptionMenuToRole(optionBranch, roleMaster);

            MuMenu optionMenuRol = this.createOrUpdateOptionMenu("Privilegios",
                    "/privilegios",
                    "icono_link",
                    2,
                    optionAdministracion);
            this.addOptionMenuToRole(optionMenuRol, roleMaster);

            MuMenu optionUser = this.createOrUpdateOptionMenu("Usuario",
                    "/usuario",
                    "icono_link",
                    3,
                    optionAdministracion);
            this.addOptionMenuToRole(optionUser, roleMaster);

            MuMenu optionParameter = this.createOrUpdateOptionMenu("Parametros",
                    "/table-list",
                    "icono_link",
                    4,
                    optionAdministracion);
            this.addOptionMenuToRole(optionParameter, roleMaster);

            MuMenu optionEntitys = this.createOrUpdateOptionMenu("Recursos",
                    "recursos",
                    "icono_config",
                    3,
                    null);
            this.addOptionMenuToRole(optionEntitys, roleMaster);

            MuMenu optionCliente = this.createOrUpdateOptionMenu("Cliente",
                    "/cliente",
                    "icono_link",
                    1,
                    optionEntitys);
            this.addOptionMenuToRole(optionCliente, roleMaster);
        }
    }

    private void addOptionMenuToRole(MuMenu OptionMenu, MuRole role){
        if (!muRoleMenuRepository.existsByRoleAndMenu(role.getId(), OptionMenu.getId())){
            this.muRoleMenuRepository.save(MuRoleMenu.builder().role(role).menu(OptionMenu).build());
        }
    }
    private MuMenu createOrUpdateOptionMenu(String name,
                                      String url,
                                      String icon,
                                      Integer sequence,
                                      MuMenu father){

        MuMenu menuOption = this.muMenuRepository.findByName(name).orElse(null);

        if (menuOption == null){
            menuOption = MuMenu.builder().
                    name(name).
                    url(url).
                    icon(icon).
                    sequence(sequence).
                    father(father).build();
        }else{
            menuOption.setName(name);
            menuOption.setUrl(url);
            menuOption.setIcon(icon);
            menuOption.setSequence(sequence);
            menuOption.setFather(father);
        }
        this.muMenuRepository.save(menuOption);

        return menuOption;
    }

    private void addParams() {
        log.info("******************************* Cargado de Parametros *******************************");

        MuParameterGroup paramGeneralCompany = this.muParameterGroupService.findByGrupo("Parametros Generales de Empresa").orElse(MuParameterGroup.builder()
                .groupName("Parametros Generales de Empresa")
                .description("Parametros Generales de Empresa")
                .build());
        this.muParameterGroupService.save(paramGeneralCompany);
        this.muParameterService.saveParameter(new MuParameter(null, Constants.Params.COUNTRY, "Pais", "Bolivia", null, null, null, null, ParameterTypeEnum.CADENA, paramGeneralCompany));
        this.muParameterService.saveParameter(new MuParameter(null, Constants.Params.COMPANY_NAME, "Empresa", "Nombre de la Empresa", null, null, null, null, ParameterTypeEnum.CADENA, paramGeneralCompany));
        this.muParameterService.saveParameter(new MuParameter(null, Constants.Params.NIT, "Nit", "1111111111", null, null, null, null, ParameterTypeEnum.CADENA, paramGeneralCompany));
        this.muParameterService.saveParameter(new MuParameter(null, Constants.Params.BUSINESS_NAME, "Razon Social", "Razon Social de la Empresa", null, null, null, null, ParameterTypeEnum.CADENA, paramGeneralCompany));
        this.muParameterService.saveParameter(new MuParameter(null, Constants.Params.COMPANY_ADDRESS, "Dirección", "Dirección de la Empresa", null, null, null, null, ParameterTypeEnum.CADENA, paramGeneralCompany));
    }

    private void addDomains(){
        log.info("******************************* Cargado de Dominios *******************************");

        MuDomain domainGender = this.muDomainRepository.findById(Constants.Domain.GENDER).orElse(MuDomain.builder()
                .id(Constants.Domain.GENDER)
                .description("Generos de Personas")
                .build());
        this.muDomainRepository.save(domainGender);
        this.addDominioGender(domainGender);

        MuDomain domainCategoryClient = this.muDomainRepository.findById(Constants.Domain.CATEGORY_CLIENTE).orElse(MuDomain.builder()
                .id(Constants.Domain.CATEGORY_CLIENTE)
                .description("Categoria de Clientes")
                .build());
        this.muDomainRepository.save(domainCategoryClient);
        this.addDominioCategoryClient(domainCategoryClient);

        MuDomain domainStateCivil = this.muDomainRepository.findById(Constants.Domain.STATE_CIVIL).orElse(MuDomain.builder()
                .id(Constants.Domain.STATE_CIVIL)
                .description("Estado Civil")
                .build());
        this.muDomainRepository.save(domainStateCivil);
        this.addDominioStateCivil(domainStateCivil);

        MuDomain domainOccupation = this.muDomainRepository.findById(Constants.Domain.OCCUPATION).orElse(MuDomain.builder()
                .id(Constants.Domain.OCCUPATION)
                .description("Ocupación de Clientes")
                .build());
        this.muDomainRepository.save(domainOccupation);
        this.addDominioOccupationClient(domainOccupation);

    }

    private void addDominioGender(MuDomain domainGender) {
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Masculino", "Masculino", domainGender.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Femenino", "Femenino", domainGender.getId()));
    }

    private void addDominioCategoryClient(MuDomain domainCategoryClient) {
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Formales", "Formales", domainCategoryClient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Semi-Formales", "Semi-Formales", domainCategoryClient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Informales", "Informales", domainCategoryClient.getId()));
    }

    private void addDominioStateCivil(MuDomain domainStateCivil) {
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Soltero", "Soltero", domainStateCivil.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Casado", "Casado", domainStateCivil.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Divorciado", "Divorciado", domainStateCivil.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Viudo", "Viudo", domainStateCivil.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Unión Libre", "Unión Libre", domainStateCivil.getId()));

    }

    private void addDominioOccupationClient(MuDomain domainOccupationCLient) {
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Ferreteria", "Ferreteria", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Lubricentro", "Lubricentro", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Panaderia", "Panaderia", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Restaurant", "Restaurant", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Carniceria", "Carniceria", domainOccupationCLient.getId()));

        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Chofer", "Chofer", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Taxista", "Taxista", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "MotoTaxista", "MotoTaxista", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Albañil", "Albañil", domainOccupationCLient.getId()));
        this.muDomainService.voidCreateMuDomainValue(new MuDomainValueDto(null, "Abarrotes", "Abarrotes", domainOccupationCLient.getId()));

    }

    private void loadTestClients(Integer registros){
        String[] categories = {"Formales", "Semi-Formales", "Informales"};
        String[] ocupacion = {"Ferreteria", "Lubricentro", "Panaderia"};
        for (int i = 0; i < registros ; i++) {
            RcClient client = new RcClient();
            client.setDni(String.format("%08d", random.nextInt(100000000)));
            client.setNames(faker.name().firstName());
            client.setLastnames(faker.name().lastName());
            client.setBirthdate(faker.date().birthday(18,60));
            client.setGender(random.nextBoolean() ? "Masculino" : "Femenino");
            client.setCivilStatus(random.nextBoolean() ? "Soltero" : "Casado");
            client.setPhone(faker.phoneNumber().cellPhone());
            client.setEmail(faker.internet().emailAddress());
            client.setIncomeMonth(faker.number().numberBetween(1000, 20000));
            client.setAddressHome(faker.address().fullAddress());
            client.setAddressBusiness(faker.address().fullAddress());
            client.setCategory(categories[random.nextInt(categories.length)]);
            client.setOccupation(ocupacion[random.nextInt(ocupacion.length)]);
            client.setComment(faker.lorem().sentence());

            this.rcClientRepository.save(client);
        }
    }
}

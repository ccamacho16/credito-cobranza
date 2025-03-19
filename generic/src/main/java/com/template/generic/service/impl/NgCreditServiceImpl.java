package com.template.generic.service.impl;

import com.template.generic.exception.OperationException;
import com.template.generic.model.*;
import com.template.generic.model.dto.NgCreditDetailDto;
import com.template.generic.model.dto.NgCreditDto;
import com.template.generic.model.dto.RcClientDto;
import com.template.generic.model.dto.select.CreditCabDto;
import com.template.generic.model.enums.CreditShareEnum;
import com.template.generic.model.enums.CreditStatusEnum;
import com.template.generic.model.enums.CreditTypePlanPaymentEnum;
import com.template.generic.model.enums.FrecuencyPaymentEnum;
import com.template.generic.repository.*;
import com.template.generic.service.NgCreditDetailService;
import com.template.generic.service.NgCreditGuarantorService;
import com.template.generic.service.NgCreditService;
import com.template.generic.util.StringUtil;
import com.template.generic.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * 11.03.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Service
public class NgCreditServiceImpl implements NgCreditService {

    @Autowired
    private NgCreditRepository ngCreditRepository;
    @Autowired
    private NgCreditDetailRepository ngCreditDetailRepository;
    @Autowired
    private NgCreditGuarantorRepository ngCreditGuarantorRepository;
    @Autowired
    private NgCreditDetailService ngCreditDetailService;
    @Autowired
    private RcClientRepository rcClientRepository;
    @Autowired
    private RcCreditOfficerRepository rcCreditOfficerRepository;
    @Autowired
    private AmBranchRepository amBranchRepository;
    @Autowired
    private NgCreditGuarantorService ngCreditGuarantorService;


    @Override
    @Transactional(readOnly = true)
    public Page<CreditCabDto> listByFilter(String filter, Pageable pageable) {
        ValidationUtil.throwExceptionIfInvalidText("Filtro", filter, false, 50);
        if (StringUtil.isEmptyOrNull(filter))
            filter = null;
        else
            filter = "%" + filter.toUpperCase() + "%";
        return  this.ngCreditRepository.pageWithFilter(filter, pageable, CreditCabDto.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NgCreditDto create(NgCreditDto dto) {

        this.validateCreateUpdate(dto);

        RcClient client = this.rcClientRepository.findById(dto.getIdClient())
                .orElseThrow(()->new OperationException("No existe un Cliente con id: "+dto.getIdClient()));

        RcCreditOfficer creditOfficer = this.rcCreditOfficerRepository.findById(dto.getIdCreditOfficer())
                .orElseThrow(()->new OperationException("No existe un Oficial de Crédito con id: "+dto.getIdCreditOfficer()));

        AmBranch branch = this.amBranchRepository.findById(dto.getIdBranch())
                .orElseThrow(()->new OperationException("No existe una Sucursal con id: "+dto.getIdBranch()));

        NgCredit ngCredit = dto.getEntity();
        ngCredit.setClient(client);
        ngCredit.setCreditOfficer(creditOfficer);
        ngCredit.setBranch(branch);
        ngCredit.setStatus(CreditStatusEnum.SOLICITADO);
        this.ngCreditRepository.save(ngCredit);

        this.ngCreditDetailService.createFromList(dto.getCreditDetailsDto(), ngCredit.getId());
        this.ngCreditGuarantorService.createFromList(dto.getIdsGuarantos(), ngCredit.getId());

        return this.findById(ngCredit.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public NgCreditDto findById(Long id) {
        NgCredit credit = this.ngCreditRepository.findById(id)
                .orElseThrow(()->new OperationException("No existe un Crédito con id: "+id));

        NgCreditDto creditDto = NgCreditDto.getDto(credit);

        creditDto.setIdClient(credit.getClient().getId());
        creditDto.setDescriptionClient(credit.getClient().getDni() + " - " + credit.getClient().getNames()+" "+credit.getClient().getLastnames());

        creditDto.setIdCreditOfficer(credit.getCreditOfficer().getId());
        creditDto.setNameCreditOfficer(credit.getCreditOfficer().getNames() + " " + credit.getCreditOfficer().getLastnames());

        creditDto.setIdBranch(credit.getBranch().getId());


        creditDto.setCreditDetailsDto(this.ngCreditDetailRepository.listFromCredit(creditDto.getId()));
        creditDto.setGuarantosDto(this.ngCreditGuarantorRepository.listGuarantorByCredit(creditDto.getId()));

        return creditDto;
    }


    // Sitema Frances (fijo) - Sistema Aleman (decreciente)
    @Override
    @Transactional(readOnly = true)
    public List<NgCreditDetailDto> generatePaymentPlan(NgCreditDto dto) {

        this.validatePaymentPlan(dto);

        int nroMeses = dto.getTerm().intValue();
        BigDecimal porcentajeInteres = dto.getInterest();
        int montoSolicitado = dto.getAmount();
        FrecuencyPaymentEnum frecuenciaPago = dto.getFrequencyPayment();
        int diasFrecuencia = this.daysFrecuency(frecuenciaPago);
        int nroCuotasPorMes = this.quotasByMonth(frecuenciaPago);
        int nroCuotas = nroMeses * nroCuotasPorMes;

        if (dto.getTypePlanPayment() == CreditTypePlanPaymentEnum.CUOTAS_FIJAS){
            return this.systemFrench(diasFrecuencia, nroCuotasPorMes, nroCuotas, porcentajeInteres, montoSolicitado);
        }else{
            return this.systemGerman(diasFrecuencia, nroCuotasPorMes, nroCuotas, porcentajeInteres, montoSolicitado);
        }
    }
    private void validatePaymentPlan(NgCreditDto dto){
        if (dto == null){
            throw new OperationException("El Credito recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionRequiredIfBlank("Tipo de Plan de Pago", dto.getTypePlanPayment().toString());
            ValidationUtil.throwExceptionRequiredIfBlank("Frecuencia de Pago", dto.getFrequencyPayment().toString());
            ValidationUtil.throwExceptionIfInvalidNumber("Monto", dto.getAmount(), true, 0);
            ValidationUtil.throwExceptionIfInvalidNumber("Plazo", dto.getTerm().intValue(), true, 0);
            ValidationUtil.throwExceptionIfInvalidBigDecimal("Interes", dto.getInterest(), true, BigDecimal.ZERO, 1);
        }
    }

    private void validateCreateUpdate(NgCreditDto dto){
        if (dto == null){
            throw new OperationException("El Credito recibido es igual a NULL");
        }else{
            ValidationUtil.throwExceptionIfInvalidDate("Fecha de Generación", dto.getGenerationDate(), true);
            ValidationUtil.throwExceptionRequiredIfBlank("Tipo de Plan de Pago", dto.getTypePlanPayment().toString());
            ValidationUtil.throwExceptionRequiredIfBlank("Frecuencia de Pago", dto.getFrequencyPayment().toString());
            ValidationUtil.throwExceptionIfInvalidNumber("Monto", dto.getAmount(), true, 0);
            ValidationUtil.throwExceptionIfInvalidNumber("Saldo Capital", dto.getCapitalBalance(), true, 0);
            ValidationUtil.throwExceptionIfInvalidNumber("Plazo", dto.getTerm().intValue(), true, 0);
            ValidationUtil.throwExceptionRequiredIfBlank("Garantia", dto.getWarranty());
            ValidationUtil.throwExceptionIfInvalidBigDecimal("Interes", dto.getInterest(), true, BigDecimal.ZERO, 1);
            ValidationUtil.throwExceptionRequiredIfBlank("Estado", dto.getStatus().toString());
            ValidationUtil.throwExceptionRequiredIfBlank("Tipo de Crédito", dto.getType().toString());
        }
    }

    public List<NgCreditDetailDto> systemGerman(int diasFrecuencia,
                                                 int nroCuotasPorMes,
                                                 int nroCuotas,
                                                 BigDecimal porcentajeInteres,
                                                 int monto){

        List<NgCreditDetailDto> planDePagos = new ArrayList<>();

        // convierte el valor de interes en decimales. Ej. 12% = 0.12
        BigDecimal tasaInteres = porcentajeInteres.divide(BigDecimal.valueOf(100), 3, RoundingMode.DOWN);

        // tasa de interes / nro de cuotas por mes.
        BigDecimal tasaFrecuencia = tasaInteres.divide(BigDecimal.valueOf(nroCuotasPorMes), 3, RoundingMode.DOWN);

        // Calcular amortización fija de capital
        BigDecimal amortizacionCapital = BigDecimal.valueOf(monto).divide(BigDecimal.valueOf(nroCuotas), 2, RoundingMode.DOWN);

        // Fecha inicial (se puede cambiar según la lógica de negocio)
        Calendar calendar = Calendar.getInstance();

        BigDecimal saldoPendiente = BigDecimal.valueOf(monto);
        BigDecimal pagoCapital;
        BigDecimal cuotaTotal = BigDecimal.ZERO;
        BigDecimal sumaDecimalesCapital = BigDecimal.ZERO;

        for (int i = 1; i <= nroCuotas; i++) {
            // Calcular interés sobre el saldo pendiente
            BigDecimal pagoInteres = saldoPendiente.multiply(tasaFrecuencia).setScale(0, RoundingMode.HALF_UP);

            // Ajustar redondeo en la última cuota
            if (i == nroCuotas) {
                pagoCapital = saldoPendiente.add(sumaDecimalesCapital);
                //cuotaTotal = pagoCapital.add(pagoInteres.add(cuotaTotal.add(sumaDecimalesCapital).setScale(0, RoundingMode.DOWN))) ;
                cuotaTotal = pagoCapital.add(pagoInteres.add(sumaDecimalesCapital)).setScale(0, RoundingMode.DOWN);
            } else {
                pagoCapital = amortizacionCapital.setScale(0, RoundingMode.DOWN);
                cuotaTotal = pagoCapital.add(pagoInteres);
                sumaDecimalesCapital = sumaDecimalesCapital.add(amortizacionCapital.subtract(amortizacionCapital.setScale(2, RoundingMode.DOWN)));
            }

            // Asignar fecha de pago
            calendar.add(Calendar.DAY_OF_MONTH, diasFrecuencia);
            Date fechaPago = calendar.getTime();

            // Crear detalle de pago
            NgCreditDetailDto detalle = new NgCreditDetailDto();
            detalle.setShareNumber((byte) i);
            detalle.setShareDate(fechaPago);
            detalle.setCapital(pagoCapital.intValue());
            detalle.setInterest(pagoInteres.intValue());
            detalle.setCapitalBalance(saldoPendiente.subtract(pagoCapital).intValue());
            detalle.setPaymentCapital(0);
            detalle.setPaymentInterest(0);
            detalle.setTotal(cuotaTotal.intValue());
            detalle.setPaymentLate(0); // Inicialmente no hay mora
            detalle.setStatus(CreditShareEnum.PENDIENTE);

            planDePagos.add(detalle);

            // Actualizar saldo pendiente
            saldoPendiente = saldoPendiente.subtract(pagoCapital);
        }

        return planDePagos;

    }

    public List<NgCreditDetailDto> systemFrench(int diasFrecuencia,
                                                int nroCuotasPorMes,
                                                int nroCuotas,
                                                BigDecimal porcentajeInteres,
                                                int monto) {

        List<NgCreditDetailDto> planDePagos = new ArrayList<>();

        // Conversión de interés de porcentaje a decimal (Ej. 12% -> 0.12)
        BigDecimal tasaInteres = porcentajeInteres.divide(BigDecimal.valueOf(100), 3, RoundingMode.DOWN);

        // Tasa de interés aplicada a la frecuencia de pago
        BigDecimal tasaFrecuencia = tasaInteres.divide(BigDecimal.valueOf(nroCuotasPorMes), 3, RoundingMode.DOWN);

        // Cálculo de la cuota fija
        BigDecimal unoMasTasa = BigDecimal.ONE.add(tasaFrecuencia);
        BigDecimal potencia = unoMasTasa.pow(-nroCuotas, new MathContext(10, RoundingMode.DOWN));
        BigDecimal cuota = BigDecimal.valueOf(monto).multiply(tasaFrecuencia).divide(BigDecimal.ONE.subtract(potencia), 10, RoundingMode.DOWN);

        // Control de fechas
        Calendar calendar = Calendar.getInstance();

        BigDecimal saldoPendiente = BigDecimal.valueOf(monto);
        BigDecimal sumaDecimalesCapital = BigDecimal.ZERO;
        BigDecimal sumaDecimalesInteres = BigDecimal.ZERO;

        for (int i = 1; i <= nroCuotas; i++) {
            // Calcular interés sobre el saldo pendiente
            BigDecimal interesExacto = saldoPendiente.multiply(tasaFrecuencia);
            BigDecimal pagoInteres = interesExacto.setScale(0, RoundingMode.HALF_UP);

            // Acumular decimales de interés
            sumaDecimalesInteres = sumaDecimalesInteres.add(interesExacto.subtract(pagoInteres));

            // Calcular capital a pagar en esta cuota
            BigDecimal pagoCapital = cuota.subtract(pagoInteres);

            if (i == nroCuotas) {
                // En la última cuota, agregar los decimales acumulados
                pagoCapital = pagoCapital.setScale(0, RoundingMode.DOWN).add(sumaDecimalesCapital.setScale(0, RoundingMode.DOWN));
                pagoInteres = pagoInteres.add(sumaDecimalesInteres.setScale(0, RoundingMode.DOWN));

                // Forzar que el saldo capital sea 0 en la última cuota
                pagoCapital = saldoPendiente; // El capital de la última cuota debe ser igual al saldo pendiente
            } else {
                pagoCapital = pagoCapital.setScale(0, RoundingMode.DOWN);
                // Acumular los decimales descartados en cada cuota
                sumaDecimalesCapital = sumaDecimalesCapital.add(cuota.subtract(pagoCapital.add(pagoInteres)));
            }

            // Calcular cuota total
            BigDecimal cuotaTotal = pagoCapital.add(pagoInteres);

            // Asignar fecha de pago
            calendar.add(Calendar.DAY_OF_MONTH, diasFrecuencia);
            Date fechaPago = calendar.getTime();

            // Crear y agregar el detalle de la cuota
            NgCreditDetailDto detalle = new NgCreditDetailDto();
            detalle.setShareNumber((byte) i);
            detalle.setShareDate(fechaPago);
            detalle.setCapital(pagoCapital.intValue()); // Garantiza valor entero
            detalle.setInterest(pagoInteres.intValue()); // Garantiza valor entero
            detalle.setCapitalBalance(saldoPendiente.subtract(pagoCapital).intValue());
            detalle.setPaymentCapital(0);
            detalle.setPaymentInterest(0);
            detalle.setTotal(cuotaTotal.intValue());
            detalle.setPaymentLate(0); // Inicialmente sin mora
            detalle.setStatus(CreditShareEnum.PENDIENTE);

            planDePagos.add(detalle);

            // Actualizar saldo pendiente
            saldoPendiente = saldoPendiente.subtract(pagoCapital);
        }

        return planDePagos;
    }

    public int quotasByMonth(FrecuencyPaymentEnum frecuenciaPago){
        return switch (frecuenciaPago.getFrecuency().toUpperCase()) {
            case "MENSUAL" -> 1;
            case "QUINCENAL" -> 2;
            case "SEMANAL" -> 4;
            default -> throw new IllegalArgumentException("Frecuencia de pago no válida: " + frecuenciaPago);
        };
    }

    public int daysFrecuency(FrecuencyPaymentEnum frecuenciaPago){
        return switch (frecuenciaPago.getFrecuency().toUpperCase()) {
            case "MENSUAL" -> 30;
            case "QUINCENAL" -> 15;
            case "SEMANAL" -> 7;
            default -> throw new IllegalArgumentException("Frecuencia de pago no válida: " + frecuenciaPago);
        };
    }
}

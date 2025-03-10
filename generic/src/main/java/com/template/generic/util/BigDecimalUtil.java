package com.template.generic.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
@Slf4j
public class BigDecimalUtil {

    public static final int QUANTITY_DECIMAL = 5;// Max 5 decimales, Operaciones
    public static final int QUANTITY_DECIMAL_SHOW = 2;

    public static BigDecimal toBigDecimal(String valor) {
        try {
            return new BigDecimal(valor);
        } catch (Exception e) {
            log.error("Se produjo un error al intentar convertir a decimal el valor {}", valor);
            return null;
        }
    }

    public static Byte toByte(String valor) {
        try {
            return Byte.parseByte(valor);
        } catch (Exception e) {
            log.error("Se produjo un error al intentar convertir a Byte el valor {}", valor);
            return null;
        }
    }

    public static boolean isZero(BigDecimal valor) {
        return valor != null && valor.compareTo(new BigDecimal("0")) == 0;
    }

    public static boolean isNullOrZero(BigDecimal valor) {
        if (valor == null) {
            return true;
        }
        return valor.compareTo(new BigDecimal("0")) == 0;
    }

    public static BigDecimal plus(BigDecimal numberA, BigDecimal numberB) {
        if (numberA == null && numberB == null)
            return BigDecimal.ZERO;

        if (numberB == null)
            return numberA;

        if (numberA == null)
            return numberB;

        return numberA.add(numberB);
    }

    public static boolean equals(BigDecimal numberA, BigDecimal numberB) {
        if (numberA == null) {
            return numberB == null;
        }
        if (numberB == null)
            return false;
        return numberA.compareTo(numberB) == 0;
    }

    public static boolean lessThanOrEqual(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) <= 0;
    }

    public static boolean greater(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0;
    }

    public static BigDecimal roundTo(BigDecimal numberA, Integer quantityDecimals) {
        return numberA.setScale(quantityDecimals, RoundingMode.HALF_UP);
    }

    public static BigDecimal roundTo(BigDecimal numberA) {
        if(numberA == null){
            return new BigDecimal("0");
        }
        return numberA.setScale(QUANTITY_DECIMAL_SHOW, RoundingMode.HALF_UP);
    }

    public static BigDecimal truncateTo(BigDecimal numberA, Integer quantityDecimals) {
        return numberA.setScale(quantityDecimals, RoundingMode.DOWN);
    }

    public static BigDecimal subtract(BigDecimal numberA, BigDecimal numberB) {
        if (numberB == null)
            return numberA;

        return numberA.subtract(numberB);
    }

    public static BigDecimal multiply(BigDecimal numberA, BigDecimal numberB) {
        return numberA.multiply(numberB);
    }

    public static BigDecimal divide(BigDecimal numberA, BigDecimal numberB) {
        BigDecimal result = numberA.divide(numberB, QUANTITY_DECIMAL + 2, RoundingMode.HALF_UP);
        result = result.stripTrailingZeros();
        return result;
    }

    public static BigDecimal divideAndRound(BigDecimal numberA, Integer quantityDecimals, BigDecimal numberB) {
        return numberA.divide(numberB, quantityDecimals, RoundingMode.HALF_UP);
    }

    public static int getQuantityDecimals(BigDecimal bigDecimal) {
        String string = bigDecimal.toString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    public static int getQuantityDigits(BigDecimal bigDecimal) {
        String string = bigDecimal.toString();
        int index = string.indexOf(".");
        int qDecimal = index < 0 ? 0 : string.length() - index - 1;
        int qPart = index < 0 ? string.length() : index;
        return qPart + qDecimal;
    }

    public static String formatMessageQr(BigDecimal value){
        BigDecimal aux = roundTo(value,2);
//        DecimalFormat df = new DecimalFormat("##.00");
//        System.out.println(df.format(value.doubleValue()));
        return aux.toPlainString().replace(".", "").replace(",","");
    }

    public static String formatStr(BigDecimal value, String formato){
        DecimalFormat df = new DecimalFormat(formato);
        return df.format(value.doubleValue());
    }

    public static boolean betweenValues(BigDecimal value, BigDecimal initialValue, BigDecimal finalValue){
        return (value.compareTo(initialValue) >= 0 && value.compareTo(finalValue) <= 0);
    }

    /**
     * A -> total
     * x -> partOfTotal
     */
    public static BigDecimal ruleOfThree(Integer A, Integer total, BigDecimal partOfTotal, int decimals) {
        if (total == null || total <= 0)
            return BigDecimal.ZERO;

        BigDecimal partOfA = divide(multiply(partOfTotal, new BigDecimal(A)), new BigDecimal(total));
        return roundTo(partOfA, decimals);
    }
    public static BigDecimal ruleOfThree(BigDecimal A, BigDecimal total, BigDecimal partOfTotal, int decimals) {
        if (total == null || total.doubleValue() <= 0)
            return BigDecimal.ZERO;

        BigDecimal partOfA = divide(multiply(partOfTotal, A), total);
        return roundTo(partOfA, decimals);
    }

    public static void main(String[] args) {
        System.out.println(formatMessageQr(new BigDecimal(0.01)));
    }
}

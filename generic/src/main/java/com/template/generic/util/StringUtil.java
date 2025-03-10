package com.template.generic.util;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class StringUtil {

    public static boolean isEmptyOrNull(String value) {
        if (value == null) {
            return true;
        }
        if (value.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String value) {
        return isEmptyOrNull(value);
    }

    public static String trimUpperCase(String valor) {
        if (valor == null) {
            return null;
        }
        valor = valor.trim();
        valor = valor.toUpperCase();
        return valor;
    }

    public String encode(String txt) {
        String base64 = byteToBase64(txt.getBytes());
        System.out.println(base64);
        System.out.println(base64.substring(0, base64.length() - 1));
        return base64.substring(0, base64.length() - 1);
    }

    public static String[] decode(String base64) {
        base64 += "=";
        System.out.println(base64);
        String txt = new String(base64ToByte(base64));
        return txt.split("\\|");
    }

    public static String byteToBase64(byte[] byteArray) {
        byte[] encoded = Base64.getEncoder().encode(byteArray);
        return new String(encoded);
    }

    public static byte[] base64ToByte(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }

    public static String leftPad(int inputString, int length, char charOfReplace) {
        return leftPad(String.valueOf(inputString), length, charOfReplace);
    }

    public static String leftPad(long inputString, int length, char charOfReplace) {
        return leftPad(String.valueOf(inputString), length, charOfReplace);
    }

    public static String leftPad(String inputString, int length, char charOfReplace) {
        return String.format("%1$" + length + "s", inputString).replace(' ', charOfReplace);
    }

    public static boolean containsWord(String mainString, String word) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE); // "\\b" represents any word boundary.
        Matcher matcher = pattern.matcher(mainString);
        return matcher.find();
    }


    public static String defaultIsEmpty(String value, String defaultValue) {
        if (value != null && value.trim().length() == 0)
            return defaultValue;

        return value;
    }

    public static String defaultIsBlank(String value, String defaultValue) {
        if (value == null || value.trim().equals("") || value.equals("null"))
            return defaultValue;

        return value;
    }

    /**
     * Método utilizado para generar numeroOrdenOriginante y destinatario no se debe modificar
     *
     * @param str
     * @return
     */
    public static String getFirstNumberOfString(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    public static boolean isShort(String cs) {
        try {
            Short.parseShort(cs);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isInt(String cs) {
        try {
            Integer.parseInt(cs);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static List<String> convertStringToList (String listString){
        return Arrays.asList(listString.split(","));
    }

}


package com.soares.cadastrocliente.api.utils;

import java.util.Collection;
import java.util.Map;

public class ObjetoUtil {

    private static final double ZERO = 0;

    /**
     * Método responsável por validar se a String informada é nula ou vazia.
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * Método responsável por
     *
     * @param str
     * @return boolean
     *
     */
    public static boolean isEmpty(String[] str) {
        return isNull(str) || str.length == 0;
    }

    /**
     * Método responsável por validar se o número informado é nulo ou igual a zero.
     *
     * @param number
     * @return boolean
     */
    public static boolean isEmpty(Number number) {
        return isNull(number) || number.doubleValue() == ZERO;
    }

    /**
     * Método responsável por validar se a lista é nula ou está vazia.
     *
     * @param lista
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> lista) {
        return isNull(lista) || lista.isEmpty();
    }

    /**
     * Método responsável por validar se o mapa é nulo ou está vazio.
     *
     * @param mapa
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> mapa) {
        return isNull(mapa) || mapa.isEmpty();
    }

    /**
     * Método responsável por validar se o objeto é nulo.
     *
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}

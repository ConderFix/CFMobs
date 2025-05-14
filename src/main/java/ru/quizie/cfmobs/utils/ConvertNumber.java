package ru.quizie.cfmobs.utils;

import java.text.DecimalFormat;

public class ConvertNumber {

    private static final DecimalFormat df = new DecimalFormat("#.#");

    public static String convent(final Double number) {
        return df.format(number);
    }
}

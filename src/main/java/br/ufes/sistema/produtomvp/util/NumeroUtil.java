package br.ufes.sistema.produtomvp.util;

import java.util.Locale;

public final class NumeroUtil {

    private static final Locale PT_BR = Locale.of("pt", "BR");

    private NumeroUtil() {}

    public static String formatar(Double valor) {
        return valor == null ? "" : String.format(PT_BR, "%.2f", valor);
    }

    public static Double parse(String texto) throws NumberFormatException {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String t = texto.trim();
        if (t.contains(",")) {
            t = t.replace(".", "").replace(",", ".");
        }
        return Double.parseDouble(t);
    }
}
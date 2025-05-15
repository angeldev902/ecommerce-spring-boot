package com.angel.server_spring_boot.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    private StringUtils() {
        // Constructor privado para evitar instanciación
    }

    public static String generateCode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // 1. Normalizar texto (remueve acentos y diacríticos)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = normalized.replaceAll("\\p{M}", ""); // Remueve acentos

        // 2. Reemplazar la "ñ" por "n"
        withoutAccents = withoutAccents.replaceAll("ñ", "n").replaceAll("Ñ", "N");

        // 3. Eliminar caracteres especiales excepto letras, números y espacios
        String cleanText = withoutAccents.replaceAll("[^a-zA-Z0-9\\s]", "");

        // 4. Reemplazar múltiples espacios con un solo espacio y trim
        cleanText = cleanText.trim().replaceAll("\\s+", "-");

        return cleanText.toLowerCase(); // Convertir a minúsculas si es necesario
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

}


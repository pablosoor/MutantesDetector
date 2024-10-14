package com.example.demo.Validators;

public class AdnValidator {
    private static final int ADN_MAX_LENGTH = 1000; // Definir el límite de tamaño

    // Método para validar la longitud del ADN
    public static boolean validateDnaLength(String[] dna) {
        int totalLength = 0;
        for (String strand : dna) {
            totalLength += strand.length(); // Sumar la longitud de cada secuencia
        }
        return totalLength <= ADN_MAX_LENGTH && dna.length > 0; // aseguro que hay al menos una fila
    }

    // Método para validar si el ADN contiene solo caracteres válidos
    public static boolean validateDnaCharacters(String[] dna) {
        for (String strand : dna) {
            for (char base : strand.toCharArray()) {
                if (base != 'A' && base != 'T' && base != 'C' && base != 'G') {
                    return false;
                }
            }
        }
        return true;
    }
}

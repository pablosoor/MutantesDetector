package com.example.demo.Entidades;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class DnaConverter implements AttributeConverter<String[], String> {

    @Override
    public String convertToDatabaseColumn(String[] dna) {
        return dna != null ? String.join(",", dna) : null; // Convierte a un String separado por comas
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        return dbData != null ? dbData.split(",") : null; // Convierte de String a arreglo
    }
}

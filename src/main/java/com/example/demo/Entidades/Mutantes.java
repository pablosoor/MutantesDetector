package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Mutante") // Nombre de la tabla en la base de datos
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mutantes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private long id;

    @Convert(converter = DnaConverter.class) // Conversor para manejar el campo ADN
    private String[] adn; // Arreglo de cadenas que representa el ADN

    @Column(name = "es_mutante") // Nombre de la columna en la base de datos
    private boolean esMutante; // Indica si el mutante es verdadero o no
}


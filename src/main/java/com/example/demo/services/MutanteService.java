package com.example.demo.services;

import com.example.demo.Entidades.Mutantes;
import com.example.demo.Repository.MutantesRepository;
import com.example.demo.Validators.AdnValidator;
import com.example.demo.Validators.InvalidDnaException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MutanteService implements BaseService<Mutantes> {

    @Autowired
    private MutantesRepository mutanteRepository;

    private static final int MIN_SEQUENCE_LENGTH = 4; // Longitud mínima para las secuencias
    private static final char[] BASES = {'A', 'T', 'C', 'G'}; // Bases del ADN

    @Override
    @Transactional
    public List<Mutantes> findAll() throws Exception {
        try {
            return mutanteRepository.findAll(); // Devuelve todos los mutantes
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Mutantes findById(Long id) throws Exception {
        Optional<Mutantes> entityOptional = mutanteRepository.findById(id);
        return entityOptional.orElseThrow(() -> new EntityNotFoundException("Mutante no encontrado")); // Busca un mutante por ID
    }

    @Override
    @Transactional
    public Mutantes save(Mutantes entity) throws Exception {
        // Valida la longitud y los caracteres del ADN antes de guardar
        if (!AdnValidator.validateDnaLength(entity.getAdn()) || !AdnValidator.validateDnaCharacters(entity.getAdn())) {
            throw new InvalidDnaException("El ADN es inválido.");
        }
        return mutanteRepository.save(entity); // Guarda el mutante
    }

    @Override
    @Transactional
    public Mutantes update(Long id, Mutantes entity) throws Exception {
        Optional<Mutantes> entityOptional = mutanteRepository.findById(id);
        Mutantes mutante = entityOptional.orElseThrow(() -> new EntityNotFoundException("Mutante no encontrado")); // Buscar mutante por ID

        // Valida el ADN antes de actualizar
        if (!AdnValidator.validateDnaLength(entity.getAdn()) || !AdnValidator.validateDnaCharacters(entity.getAdn())) {
            throw new InvalidDnaException("El ADN es inválido.");
        }

        return mutanteRepository.save(mutante); // Actualiza el mutante
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        // Verifica si el mutante existe antes de eliminarlo
        if (mutanteRepository.existsById(id)) {
            mutanteRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Mutante no encontrado");
        }
    }

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0; // Contador de secuencias encontradas

        // Comprobar filas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
                if (checkSequence(dna[i].substring(j, j + MIN_SEQUENCE_LENGTH))) {
                    count++;
                }
            }
        }

        // Comprobar columnas
        for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
            for (int j = 0; j < n; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
                    sb.append(dna[i + k].charAt(j));
                }
                if (checkSequence(sb.toString())) {
                    count++; // Incrementa el contador si se encuentra una secuencia
                }
            }
        }

        // Comprobar diagonales
        for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
                    sb1.append(dna[i + k].charAt(j + k)); // Diagonal de izquierda a derecha
                    sb2.append(dna[i + k].charAt(j + MIN_SEQUENCE_LENGTH - 1 - k)); // Diagonal de derecha a izquierda
                }
                if (checkSequence(sb1.toString()) || checkSequence(sb2.toString())) {
                    count++;
                }
            }
        }

        return count > 1;  // Devuelve verdadero si se encuentran más de una secuencia de mutantes
    }

    private boolean checkSequence(String sequence) {
        // Comprueba si la secuencia está compuesta solo por un tipo de base
        for (char base : BASES) {
            if (sequence.equals(new String(new char[MIN_SEQUENCE_LENGTH]).replace('\0', base))) {
                return true;
            }
        }
        return false;
    }
}

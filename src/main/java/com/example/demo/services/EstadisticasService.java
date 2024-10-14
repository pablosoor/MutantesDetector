package com.example.demo.services;

import com.example.demo.Repository.MutantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EstadisticasService {

    @Autowired
    private MutantesRepository mutanteRepository;

    public Map<String, Double> getStatistics() throws Exception {
        try {
            // Cuenta el total de mutantes
            long totalMutantes = mutanteRepository.count();
            // Calcula el total de humanos restando los mutantes
            long totalHumanos = mutanteRepository.count() - totalMutantes;


            double ratio = totalHumanos == 0 ? 0 : (double) totalMutantes / totalHumanos;

            // mapa para almacenar las estadísticas
            Map<String, Double> stats = new HashMap<>();
            stats.put("count_mutant_dna", (double) totalMutantes); //  ADN mutante
            stats.put("count_human_dna", (double) totalHumanos); //  ADN humano
            stats.put("ratio", ratio); // Razón mutantes/humanos

            return stats; // Devuelve las estadísticas
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

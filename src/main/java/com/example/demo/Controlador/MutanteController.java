package com.example.demo.Controlador;

import com.example.demo.Entidades.Mutantes; // Cambiado a Mutante
import com.example.demo.services.EstadisticasService;
import com.example.demo.services.MutanteService; // Cambiado a MutanteService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Permite acceso desde distintos orígenes/clientes
@RequestMapping(path = "api/v1/mutantes") // URI para acceder a los métodos de mutantes
public class MutanteController {
    private final MutanteService mutanteService;

    public MutanteController(MutanteService mutanteService) {
        this.mutanteService = mutanteService;
    }

    @Autowired
    private EstadisticasService estadisticasService;//servicio para estadisticas
    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estadisticasService.getStatistics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al obtener estadísticas\"}");
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getAll() { // Devuelve todos los mutantes
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findAll()); // Devuelve la lista de mutantes
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findById(id)); // Devuelve mutante por ID
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Mutantes entity) { //Guarda un nuevo mutante
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Mutantes entity) {//actualiza mutante existente
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {//eliminar mutante por id
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mutanteService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}

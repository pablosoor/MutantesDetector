package com.example.demo.services;

import java.util.List;

public interface BaseService<E> {
    List<E> findAll() throws Exception; // Trae una lista de todos los mutantes que se encuentran en la base de datos
    E findById(Long id) throws Exception; // Trae un mutante de acuerdo a su Id
    E save(E entity) throws Exception; // Crea una nueva entidad, y para eso le enviamos una nueva entidad al repositorio
    E update(Long id, E entity) throws Exception; // Trae la entidad actualizada
    boolean delete(Long id) throws Exception; // Elimina un registro de la base de datos
}

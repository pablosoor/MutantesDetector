package com.example.demo.Repository;

import com.example.demo.Entidades.Mutantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantesRepository extends JpaRepository<Mutantes, Long> {

}

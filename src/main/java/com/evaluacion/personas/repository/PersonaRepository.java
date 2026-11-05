package com.evaluacion.personas.repository;

import com.evaluacion.personas.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNroDocumento(String nroDocumento);
    boolean existsByNroDocumento(String nroDocumento);
    boolean existsByNroDocumentoAndIdNot(String nroDocumento, Long id);
}
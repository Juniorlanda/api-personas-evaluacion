package com.evaluacion.personas.service;

import com.evaluacion.personas.dto.PersonaRequestDTO;
import com.evaluacion.personas.dto.PersonaResponseDTO;

import java.util.List;

public interface PersonaService {
    PersonaResponseDTO crear(PersonaRequestDTO request);
    PersonaResponseDTO obtenerPorId(Long id);
    List<PersonaResponseDTO> listarTodos();
    PersonaResponseDTO actualizar(Long id, PersonaRequestDTO request);
    void eliminar(Long id);
    PersonaResponseDTO obtenerPorDni(String dni);
}
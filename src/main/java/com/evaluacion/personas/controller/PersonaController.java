package com.evaluacion.personas.controller;

import com.evaluacion.personas.dto.PersonaRequestDTO;
import com.evaluacion.personas.dto.PersonaResponseDTO;
import com.evaluacion.personas.service.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaResponseDTO> crear(@Valid @RequestBody PersonaRequestDTO request) {
        return new ResponseEntity<>(personaService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(personaService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PersonaRequestDTO request) {
        return ResponseEntity.ok(personaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PersonaResponseDTO> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(personaService.obtenerPorDni(dni));
    }
}
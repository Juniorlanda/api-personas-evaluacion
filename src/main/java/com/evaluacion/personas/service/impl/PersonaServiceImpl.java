package com.evaluacion.personas.service.impl;

import com.evaluacion.personas.dto.PersonaRequestDTO;
import com.evaluacion.personas.dto.PersonaResponseDTO;
import com.evaluacion.personas.entity.Persona;
import com.evaluacion.personas.exception.BusinessException;
import com.evaluacion.personas.exception.ResourceNotFoundException;
import com.evaluacion.personas.repository.PersonaRepository;
import com.evaluacion.personas.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Override
    @Transactional
    public PersonaResponseDTO crear(PersonaRequestDTO request) {
        if (personaRepository.existsByNroDocumento(request.getNroDocumento())) {
            throw new BusinessException("El número de documento ya se encuentra registrado: " + request.getNroDocumento());
        }

        Persona persona = mapToEntity(request);
        Persona saved = personaRepository.save(persona);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponseDTO obtenerPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));
        return mapToDTO(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> listarTodos() {
        return personaRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @Transactional
    public PersonaResponseDTO actualizar(Long id, PersonaRequestDTO request) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));

        if (personaRepository.existsByNroDocumentoAndIdNot(request.getNroDocumento(), id)) {
            throw new BusinessException("El número de documento ya está asignado a otra persona: " + request.getNroDocumento());
        }

        persona.setPaterno(request.getPaterno());
        persona.setMaterno(request.getMaterno());
        persona.setNombres(request.getNombres());
        persona.setTipoDocumento(request.getTipoDocumento());
        persona.setNroDocumento(request.getNroDocumento());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setSexo(request.getSexo());
        persona.setEstadoCivil(request.getEstadoCivil());
        persona.setCorreo(request.getCorreo());
        persona.setCelular(request.getCelular());

        return mapToDTO(personaRepository.save(persona));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponseDTO obtenerPorDni(String dni) {
        Persona persona = personaRepository.findByNroDocumento(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con DNI/Documento: " + dni));
        return mapToDTO(persona);
    }

    private Persona mapToEntity(PersonaRequestDTO dto) {
        return Persona.builder()
                .paterno(dto.getPaterno())
                .materno(dto.getMaterno())
                .nombres(dto.getNombres())
                .tipoDocumento(dto.getTipoDocumento())
                .nroDocumento(dto.getNroDocumento())
                .fechaNacimiento(dto.getFechaNacimiento())
                .sexo(dto.getSexo())
                .estadoCivil(dto.getEstadoCivil())
                .correo(dto.getCorreo())
                .celular(dto.getCelular())
                .build();
    }

    private PersonaResponseDTO mapToDTO(Persona entity) {
        return PersonaResponseDTO.builder()
                .id(entity.getId())
                .paterno(entity.getPaterno())
                .materno(entity.getMaterno())
                .nombres(entity.getNombres())
                .tipoDocumento(entity.getTipoDocumento())
                .nroDocumento(entity.getNroDocumento())
                .fechaNacimiento(entity.getFechaNacimiento())
                .edad(entity.getEdad())
                .sexo(entity.getSexo())
                .estadoCivil(entity.getEstadoCivil())
                .correo(entity.getCorreo())
                .celular(entity.getCelular())
                .build();
    }
}
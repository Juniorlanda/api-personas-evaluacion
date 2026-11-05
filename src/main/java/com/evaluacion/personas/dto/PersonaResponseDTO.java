package com.evaluacion.personas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponseDTO {
    private Long id;
    private String paterno;
    private String materno;
    private String nombres;
    private String tipoDocumento;
    private String nroDocumento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private Integer edad;
    private String sexo;
    private String estadoCivil;
    private String correo;
    private String celular;
}
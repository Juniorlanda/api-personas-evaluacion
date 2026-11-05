package com.evaluacion.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String paterno;

    @Column(nullable = false, length = 50)
    private String materno;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 20)
    private String tipoDocumento;

    @Column(nullable = false, unique = true, length = 15)
    private String nroDocumento;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 20)
    private String sexo;

    @Column(nullable = false, length = 20)
    private String estadoCivil;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 15)
    private String celular;

    public Integer getEdad() {
        if (this.fechaNacimiento == null) return null;
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}
package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Paciente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria autogenerada
    @Column(name = "socialWork")
    private String socialWork;
    @Column(name = "codeSocialWork")
    private String codeSocialWork;
    @Column(name = "idPaciente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;  // Clave primaria autogenerada
}


package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    public Paciente(Long id){
        this.id = id;
    }
}


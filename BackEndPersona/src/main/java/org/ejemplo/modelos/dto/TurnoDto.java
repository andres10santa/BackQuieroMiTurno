package org.ejemplo.modelos.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TurnoDto {

    private String paciente_id;
    private String  doctor_id;

    private LocalDateTime fechaGuardado;

    private String fechaTurno;

}

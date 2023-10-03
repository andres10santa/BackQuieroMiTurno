package org.ejemplo.validations;

import org.ejemplo.exception.TurnoException;
import org.ejemplo.modelos.Turno;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class TurnoValidations {

    public static boolean validateExistTurno(List<Turno> turnos, Date fechaTurno) {
        for (Turno turno : turnos) {
            if (turno.getFechaTurno().equals(fechaTurno)) {
                return true;
            }
        }
        return false;
    }

    public static void validateTurnoForCreation(List<Turno> turnos, Turno turno) throws TurnoException {
        if (turno.getFechaTurno() != null){
            throw new TurnoException(HttpStatus.PRECONDITION_FAILED, "Error en el turno", "Ya existe un turno para esta fecha");
        }

        if(validateExistTurno(turnos, turno.getFechaTurno())){
            throw new TurnoException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el turno " + turno.getFechaTurno(), "El Turno ya se encuentra cargado");
        }

        // Aquí puedes agregar más validaciones según tus requisitos
    }
}

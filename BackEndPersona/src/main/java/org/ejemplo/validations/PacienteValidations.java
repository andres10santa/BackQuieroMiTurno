package org.ejemplo.validations;

import org.ejemplo.exception.PacienteException;
import org.ejemplo.modelos.Paciente;

import org.springframework.http.HttpStatus;

import java.util.List;

public class PacienteValidations {
    public static Boolean validateExistPaciente(List<Paciente> pacientes, String username){
        for(Paciente user: pacientes){
            if (user.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void validatePacienteForRegister(List<Paciente> pacientes, Paciente paciente) throws PacienteException {
        if (validateStringNotEmptyNotNull(paciente.getEmail())){
            throw new PacienteException(HttpStatus.PRECONDITION_FAILED,"Error en el campo paciente", "No se permite valor nulo");
        }

        if(validateExistPaciente(pacientes, paciente.getEmail())){
            throw new PacienteException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el paciente " + paciente.getEmail(), "El usuario ya se encuentra registrado");
        }
//        if(validateStringNotEmptyNotNull(paciente.getRole())||(!paciente.getRole().equalsIgnoreCase("administrador")&&!paciente.getRole().equalsIgnoreCase("vendedor"))){
//            throw new PacienteException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el paciente"+paciente.getEmail(),"Porque el rol es incorrecto");
//        }
       }

    private static boolean validateStringNotEmptyNotNull(String string) {
        return string == null || string.isBlank();
    }
}


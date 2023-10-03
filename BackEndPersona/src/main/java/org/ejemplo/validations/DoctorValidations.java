package org.ejemplo.validations;

import org.ejemplo.exception.DoctorException;
import org.ejemplo.modelos.Doctor;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DoctorValidations {

    public static Boolean validateExistDoctor(List<Doctor> doctores, String username){
        for(Doctor user: doctores){
            if (user.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void validateDoctorForRegister(List<Doctor> doctores, Doctor doctor) throws DoctorException {
        if (doctor.getEmail() != null){
            throw new DoctorException(HttpStatus.PRECONDITION_FAILED,"Error en el campo doctor", "No se permite valor nulo");
        }

        if(validateExistDoctor(doctores, doctor.getEmail())){
            throw new DoctorException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el doctor " + doctor.getEmail(), "El usuario ya se encuentra registrado");
        }
//        if(validateStringNotEmptyNotNull(doctor.getRole())||(!doctor.getRole().equalsIgnoreCase("administrador")&&!doctor.getRole().equalsIgnoreCase("doctor"))){
//            throw new DoctorException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el doctor"+ doctor.getEmail(),"Porque el rol es incorrecto");
//        }
    }

}



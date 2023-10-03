package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.DoctorException;
import org.ejemplo.modelos.Doctor;
import org.ejemplo.repository.DoctorRepository;
import org.ejemplo.validations.DoctorValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DoctorService {
    List<Doctor> doctores = new ArrayList<>();
    @Autowired
    DoctorRepository doctorRepository;

    public String guardarDoctor(Doctor doctor) throws DoctorException {
        DoctorValidations.validateDoctorForRegister(doctores, doctor);
        doctorRepository.save(doctor);
        return "Doctor cargado correctamente";
    }

    public List<Doctor> retornarPDoctor(){
        return doctorRepository.findAll();
    }

    public void borrarPDoctor(String user){
        if (doctorRepository.existsById(user)){
            doctorRepository.deleteById(user);
        }
        log.warn("El usuario %s no existe y no lo podemos borrar", user);
    }

//    public String login(Login login){
//        Optional<Paciente> optionalPaciente = pacienteRepository.findById(login.getEmail());
//        if (optionalPaciente.isPresent()){
//            Paciente paciente = optionalPaciente.get();
//            if (paciente.getPassword().equals(login.getPassword())){
//                return paciente.getRole();
//            }
//        }
//        return String.format("Error, Tus datos de inicio de session son invalidos", login.getEmail());
//    }


    private Doctor getUser(String userName){
        for (Doctor usuario: doctores){
            if (usuario.getEmail().equals(userName)){
                return usuario;
            }
        }
        return null;
    }
}


package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.PacienteException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Paciente;
import org.ejemplo.repository.PacienteRepository;
import org.ejemplo.validations.PacienteValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PacienteService {
    List<Paciente> pacientes = new ArrayList<>();
    @Autowired
    PacienteRepository pacienteRepository;

    public String guardarPaciente(Paciente paciente) throws PacienteException{
        PacienteValidations.validatePacienteForRegister(pacientes, paciente);
        pacienteRepository.save(paciente);
        return "Paciente cargado correctamente";
    }

    public List<Paciente> retornarPaciente(){
        return pacienteRepository.findAll();
    }

    public void borrarPaciente(String user){
        if (pacienteRepository.existsById(user)){
            pacienteRepository.deleteById(user);
        }
        log.warn("El usuario %s no existe y no lo podemos borrar", user);
    }



    private Paciente getUser(String userName){
        for (Paciente usuario: pacientes){
            if (usuario.getEmail().equals(userName)){
                return usuario;
            }
        }
        return null;
    }
    public List<Paciente> mostrarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }
}

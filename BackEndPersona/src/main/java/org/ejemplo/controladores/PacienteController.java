package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.PacienteException;
import org.ejemplo.exception.UserException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Paciente;
import org.ejemplo.servicios.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PacienteController {
    @Autowired
    public PacienteService service;

    @PostMapping("/registryPaciente")
    public ResponseEntity<String> createPaciente(@RequestBody Paciente paciente){
        try{
            String respuesta = service.guardarPaciente(paciente);
            log.info("Paciente creado de forma correcta {}", paciente.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (PacienteException e){
            log.warn("No se esta cumpliendo con las validaciones. Paciente a crear: {}", paciente);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/getAllPaciente")
    public ResponseEntity<List<Paciente>> getAll(){
        return ResponseEntity.ok(service.retornarPaciente());
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Login login) {
//        String respuesta = service.login(login);
//        if (respuesta.contains("Error")){
//            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
//        }
//        return ResponseEntity.ok(respuesta);
//    }
}

package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.DoctorException;
import org.ejemplo.modelos.Doctor;
import org.ejemplo.servicios.DoctorService;
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
public class DoctorController {

    @Autowired
    public DoctorService service;

    @PostMapping("/registryDoctor")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor){
        try{
            String respuesta = service.guardarDoctor(doctor);
            log.info("Doctor creado de forma correcta {}", doctor.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (DoctorException e){
            log.warn("No se esta cumpliendo con las validaciones. Doctor a crear: {}", doctor);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/getAllDoctor")
    public ResponseEntity<List<Doctor>> getAll(){
        return ResponseEntity.ok(service.retornarPDoctor());
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


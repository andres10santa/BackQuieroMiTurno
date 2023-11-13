package org.ejemplo.controladores;
import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.TurnoException;
import org.ejemplo.modelos.Turno;
import org.ejemplo.modelos.dto.TurnoDto;
import org.ejemplo.servicios.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j

public class TurnoController {

    @Autowired
    public TurnoService turnoService;


    @PostMapping("/createTurno")
    public ResponseEntity<String> createTurno(@RequestBody TurnoDto turno) {
        try{
            String respuesta = turnoService.guardarTurno(turno);
            log.info("Turno creado de forma correcta");
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (TurnoException e){
            log.warn("No se esta cumpliendo con las validaciones. Turno a crear: {}", turno);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
//         Luego, puedes crear el turno
//        turno = turnoService.createTurno(turno.getPaciente(), turno.getDoctor(), turno.getFechaTurno());
//
//        return ResponseEntity.ok("Turno creado con éxito. ID: " + turno.getId());
    }

    @GetMapping("/getAllTurno")
    public ResponseEntity<List<Turno>> getAll(){
        return ResponseEntity.ok(turnoService.retornarTurnos());
    }

    @DeleteMapping("/deleteTurno/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable(value = "id") String Id){
        try{
            TurnoService.borrarTurno (Id);
            log.info("Turno borrado de forma correcta {}", Id);
            return ResponseEntity.status(HttpStatus.OK).body("Turno borraddo de forma correcta");
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

}
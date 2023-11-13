package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.TurnoException;
import org.ejemplo.modelos.Doctor;
import org.ejemplo.modelos.Paciente;
import org.ejemplo.modelos.Turno;
import org.ejemplo.modelos.dto.TurnoDto;
import org.ejemplo.repository.TurnoRepository;
import org.ejemplo.validations.TurnoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TurnoService {

    static List<Turno> turnos = new ArrayList<>();
    private static TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public  Turno createTurno(Paciente paciente, Doctor doctor, Date fechaTurno, LocalDateTime fechaGuardado) throws TurnoException {
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setDoctor(doctor);
        turno.setFechaGuardado(fechaGuardado); // Fecha actual
        turno.setFechaTurno(String.valueOf(fechaTurno));
        return turnoRepository.save(turno);
    }


    public static String guardarTurno(TurnoDto turnoDto) throws TurnoException {


        Turno turno = new Turno();
        turno.setDoctor(new Doctor(Long.parseLong(turnoDto.getDoctor_id())));
        turno.setPaciente(new Paciente(Long.parseLong(turnoDto.getPaciente_id())));
        turno.setFechaTurno(turnoDto.getFechaTurno());
        TurnoValidations.validateTurnoForCreation(turnos, turno);
        Date hoy = new Date();
        turno.setFechaGuardado(hoy.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        turnoRepository.save(turno);
        return "Turno cargado correctamente";
    }

    public List<Turno> retornarTurnos(){
        return turnoRepository.findAll();
    }

    public static void borrarTurno(String Id){
        if (turnoRepository.existsById(Id)){
            turnoRepository.deleteById(Id);
        }
        log.warn("El turno %s no existe y no lo podemos borrar", Id);
    }
}

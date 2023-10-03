package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Turnos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaGuardado;

    @Temporal(TemporalType.DATE)
    private Date fechaTurno;


    // getters y setters
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setFechaGuardado(LocalDateTime fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }
    public LocalDateTime getFechaGuardado() {
        return fechaGuardado;
    }
    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
    public Date getFechaTurno() {
        return fechaTurno;
    }

    public String getId() {
        return null;
    }
}

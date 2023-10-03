package org.ejemplo.repository;


import org.ejemplo.modelos.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, String> {
}
package org.ejemplo.repository;

import org.ejemplo.modelos.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
}
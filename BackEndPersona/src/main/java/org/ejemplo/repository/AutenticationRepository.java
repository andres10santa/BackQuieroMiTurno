package org.ejemplo.repository;

import org.ejemplo.modelos.Autentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutenticationRepository extends JpaRepository<Autentication, String> {

    Optional<Autentication> findByToken(String token);
}

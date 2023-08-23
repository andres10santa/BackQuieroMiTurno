package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.UserException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.servicios.UsersService;
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
public class UsuarioController {
    @Autowired
    public UsersService service;

    @PostMapping("/registry")
    public ResponseEntity<String> createUser(@RequestBody Usuario usuario){
        try{
            String respuesta = service.guardarUsuario(usuario);
            log.info("Usuario creado de forma correcta {}", usuario.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (UserException e){
            log.warn("No se esta cumpliendo con las validaciones. Usuario a crear: {}", usuario);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(service.retornarUsuarios());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String respuesta = service.login(login);
        if (respuesta.contains("Error")){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }

}

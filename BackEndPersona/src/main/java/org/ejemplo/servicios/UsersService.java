package org.ejemplo.servicios;

import jakarta.persistence.NonUniqueResultException;
import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.UserException;
import org.ejemplo.exception.ValidationException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Usuario;

import org.ejemplo.modelos.dto.LoginDTO;
import org.ejemplo.validations.UserValidations;
import org.ejemplo.validations.ValidationsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.ejemplo.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsersService {
    List<Usuario> usuarios = new ArrayList<>();
    private static ValidationsInterface<Usuario, String, Object> validations = (ValidationsInterface<Usuario, String, Object>) new UserValidations();
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AutenticationService autenticationService;

    public String guardarUsuario(Usuario usuario) throws ValidationException {
        validations.validateToCreate(retornarUsuarios(), usuario, null);
        usuarioRepository.save(usuario);
        return "usuario cargado correctamente";
    }

    public List<Usuario> retornarUsuarios(){
        return usuarioRepository.findAll();
    }

    public void borrarUsuarios(Long user){
        if (usuarioRepository.existsById(user)){
            usuarioRepository.deleteById(user);
        }
        log.warn("El usuario %s no existe y no lo podemos borrar", user);
    }

    public LoginDTO login(Login login) throws UserException {
        try {
            String userEmail = login.getEmail(); // Obtener el correo electrónico del objeto Login

            // Verificar que el correo electrónico no sea nulo
            if (userEmail == null) {
                throw new UserException(HttpStatus.BAD_REQUEST, "Correo electrónico nulo", "El correo electrónico no puede ser nulo");
            }

            List<Usuario> usuarios = usuarioRepository.findByEmail(userEmail);

            // Verificar que la lista de usuarios no esté vacía
            if (usuarios.isEmpty()) {
                throw new UserException(HttpStatus.UNAUTHORIZED, "Login fallido", "Tus datos de inicio de sesión son inválidos");
            }

            // Iterar sobre la lista de usuarios y verificar la contraseña
            for (Usuario usuario : usuarios) {
                if (usuario.getPassword().equals(login.getPassword())) {
                    return autenticationService.createToken(usuario);
                }
            }

            // Si no se encuentra ninguna coincidencia de contraseña
            throw new UserException(HttpStatus.UNAUTHORIZED, "Login fallido", "Tus datos de inicio de sesión son inválidos");

        } catch (NullPointerException e) {
            // Manejar la excepción cuando el ID es nulo
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", "El ID del usuario es nulo");
        } catch (NonUniqueResultException e) {
            // Manejar la excepción según sea necesario (por ejemplo, log, lanzar una excepción personalizada, etc.)
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", "La consulta devolvió más de un resultado único");
        }
    }




    private Usuario getUser(String userName){
        for (Usuario usuario: usuarios){
            if (usuario.getEmail().equals(userName)){
                return usuario;
            }
        }
        return null;
    }
}

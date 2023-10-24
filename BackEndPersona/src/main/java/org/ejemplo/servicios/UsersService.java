package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exception.UserException;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Usuario;

import org.ejemplo.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersService {
    List<Usuario> usuarios = new ArrayList<>();
    @Autowired
    UsuarioRepository usuarioRepository;

    public String guardarUsuario(Usuario usuario) throws UserException {
        UserValidations.validateUserForRegister(usuarios, usuario);
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

    public String login(Login login){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(login.getEmail());
        if (optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            if (usuario.getPassword().equals(login.getPassword())){
                // Generar un token JWT para el usuario
                String token = generateToken(usuario);

                // Almacenar el token en el cliente
                // ...

                return usuario.getSurname();
            }
        }
        return String.format("Error, Tus datos de inicio de session son invalidos", login.getId());

    }


    private Usuario getUser(String userName){
        for (Usuario usuario: usuarios){
            if (usuario.getEmail().equals(userName)){
                return usuario;
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioByToken(String token) {
        // Validar el token
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        DecodedJWT decodedJWT = JWT.decode(token, algorithm);

        // Verificar que el token no haya caducado
        Date expiresAt = decodedJWT.getClaim("expires_at").asDate();
        if (expiresAt.before(new Date())) {
            throw new UnauthorizedException("Invalid token");
        }

        // Verificar que el token sea válido para el usuario
        int userId = decodedJWT.getClaim("user_id").asInt();
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return usuario;
    }

    private String generateToken(Usuario usuario) {
        // Generar un token JWT para el usuario
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withClaim("user_id", usuario.getId())
                .withClaim("username", usuario.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .sign(algorithm);

        return token;
    }
}

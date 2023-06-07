package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.validations.UserValidations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersService {
    List<Usuario> usuarios = new ArrayList<>();

    public String guardarUsuario(Usuario usuario){
        if (UserValidations.validateExistUser(usuarios, usuario.getUser())){
            return "error, el usuario ya existe";
        }
        usuarios.add(usuario);
        return "usuario cargado correctamente";
    }

    public List<Usuario> retornarUsuarios(){
        return usuarios;
    }

    public void borrarUsuarios(String user){
        if (UserValidations.validateExistUser(usuarios, user)){
            Usuario usuarioABorrar = getUser(user);
            usuarios.remove(usuarioABorrar);
        }
        log.warn("El usuario %s no existe y no lo podemos borrar", user);
    }

    public String login(Login login){
        Usuario usuario = getUser(login.getUser());
        if (usuario != null){
            if (usuario.getPassword().equals(login.getPassword())){
                return usuario.getRole();
            }
            else {
                return "Error, contraseña incorrecta";
            }
        }
        return String.format("Error, el usuario %s no existe, por favor registrese", login.getUser());
    }


    private Usuario getUser(String userName){
        for (Usuario usuario: usuarios){
            if (usuario.getUser().equals(userName)){
                return usuario;
            }
        }
        return null;
    }
}

package org.ejemplo.validations;



import org.ejemplo.modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.ejemplo.exception.UserException;


import java.util.List;

public class UserValidations {
    public static Boolean validateExistUser(List<Usuario> usuarios, String username){
        for(Usuario user: usuarios){
            if (user.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void validateUserForRegister(List<Usuario> usuarios, Usuario usuario) throws UserException {
        if (validateStringNotEmptyNotNull(usuario.getEmail())){
            throw new UserException(HttpStatus.PRECONDITION_FAILED,"Error en el campo usuario", "No se permite valor nulo");
        }

        if(validateExistUser(usuarios, usuario.getEmail())){
            throw new UserException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el usuario " + usuario.getEmail(), "El usuario ya se encuentra registrado");
        }
//        if(validateStringNotEmptyNotNull(usuario.getRole())||(!usuario.getRole().equalsIgnoreCase("administrador")&&!usuario.getRole().equalsIgnoreCase("vendedor"))){
//            throw new UserException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el usuario"+usuario.getRole(),"Porque el rol es incorrecto");
//        }
        }

    private static boolean validateStringNotEmptyNotNull(String string) {
        return string == null || string.isBlank();
    }
}


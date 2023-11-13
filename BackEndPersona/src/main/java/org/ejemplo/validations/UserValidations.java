package org.ejemplo.validations;



import org.ejemplo.exception.ValidationException;
import org.ejemplo.modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.ejemplo.exception.UserException;


import java.util.List;

public class UserValidations implements ValidationsInterface<Usuario, String, Object> {

    @Override
    public void validateToCreate(List<Usuario> objectList, Usuario objectToCreate, Object anotherObjectRequired) throws ValidationException {
        // Implementación de la validación para la creación
    }

    @Override
    public void validateToUpdate() {
        // Implementación de la validación para la actualización
    }

    @Override
    public void validateToDelete(List<Usuario> objectList, String keyToCreate) throws ValidationException {
        // Implementación de la validación para la eliminación
    }
}


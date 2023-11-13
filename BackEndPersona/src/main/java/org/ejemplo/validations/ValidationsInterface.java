package org.ejemplo.validations;



import org.ejemplo.exception.ValidationException;

import java.util.List;

public interface ValidationsInterface<T, KEY, OBJECT> {
    void validateToCreate(List<T> obtectList, T objectToCreate, OBJECT anotherObjectRequired) throws ValidationException;
    void validateToUpdate();
    void validateToDelete(List<T> obtectList, KEY keyToCreate) throws ValidationException;
}

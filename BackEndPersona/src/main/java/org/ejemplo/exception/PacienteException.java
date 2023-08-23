package org.ejemplo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PacienteException extends Exception {
    private HttpStatus statusCode;
    private String causa;

    public PacienteException(HttpStatus status, String message, String cause) {
        super(message);
        this.causa = cause;
        this.statusCode = status;
    }
}

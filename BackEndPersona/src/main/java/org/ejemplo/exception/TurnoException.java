package org.ejemplo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TurnoException extends Exception {
    private HttpStatus statusCode;
    private String causa;

    public TurnoException(HttpStatus status, String message, String cause) {
        super(message);
        this.causa = cause;
        this.statusCode = status;
    }
}
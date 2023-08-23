package org.ejemplo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException extends Exception {
    private HttpStatus statusCode;
    private String causa;

    public UserException(HttpStatus status, String message, String cause) {
        super(message);
        this.causa = cause;
        this.statusCode = status;
    }
}

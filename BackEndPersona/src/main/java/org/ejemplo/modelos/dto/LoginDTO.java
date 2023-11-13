package org.ejemplo.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    String email;
    String rol;
    String token;
}


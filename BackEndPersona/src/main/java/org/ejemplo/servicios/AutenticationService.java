package org.ejemplo.servicios;

import org.ejemplo.modelos.Autentication;

import org.ejemplo.modelos.Usuario;
import org.ejemplo.modelos.dto.LoginDTO;
import org.ejemplo.repository.AutenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutenticationService {

    @Autowired
    private AutenticationRepository repository;
    public LoginDTO createToken(Usuario usuario){
        if (repository.existsById(usuario.getEmail())){
            repository.deleteById(usuario.getEmail());
        }
        Autentication autentication = new Autentication();
        autentication.setEmail(usuario.getEmail());
        autentication.setToken(usuario.getEmail() + UUID.randomUUID());
        autentication.setVencimiento(getVencimiento());
        repository.save(autentication);
        return new LoginDTO(usuario.getEmail(), usuario.getRole(), autentication.getToken());
    }

    public Autentication validarToken(String token) throws AuthenticationException {
        Optional<Autentication> optionalAutentication = repository.findByToken(token);
        if (optionalAutentication.isEmpty()){
            throw new AuthenticationException("El token no existe");
        }
        Autentication autentication = optionalAutentication.get();
        if (autentication.getVencimiento().before(new Date())){
            throw new AuthenticationException("El token está vencido");
        }
        autentication.setVencimiento(getVencimiento());
        repository.save(autentication);
        return autentication;
    }



    private Date getVencimiento(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, 15);
        return c.getTime();
    }
}

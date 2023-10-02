package com.gestion.empleados.demo.modelo.Excepciones;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public static final long serialVersionUID=1L;
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}

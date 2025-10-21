package com.adoo.album.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con id: " + id);
    }

    public UserNotFoundException(String username) {
    super("Usuario " + username + " no encontrado.");
    }
}


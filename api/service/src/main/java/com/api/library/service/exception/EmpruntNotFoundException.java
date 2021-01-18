package com.api.library.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Emprunt not found")
public class EmpruntNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmpruntNotFoundException( String errorMessage){
        super(errorMessage);
    }
}


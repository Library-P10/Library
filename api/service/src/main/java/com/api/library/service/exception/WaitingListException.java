package com.api.library.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "WaitingList not available")
public class WaitingListException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public WaitingListException(final String message) {
        super(message);
    }
}

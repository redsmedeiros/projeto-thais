package com.rodolpho.projetothais.exception;

import org.springframework.http.HttpStatus;

public class NurseAPIException extends RuntimeException {

    private HttpStatus status;

    private String message;

    public NurseAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NurseAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.champs.sprinboot2_essentials.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //retorna o status 400
public class BadRequestException extends RuntimeException {
    //construtor para mandar para o super
    //sempre retorne o status bad request 400
    public BadRequestException(String message) {
        super(message);
    }
}

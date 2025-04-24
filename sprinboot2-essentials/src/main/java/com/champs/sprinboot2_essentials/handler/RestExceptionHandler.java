package com.champs.sprinboot2_essentials.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.champs.sprinboot2_essentials.exception.BadRequestException;
import com.champs.sprinboot2_essentials.exception.BadRequestExceptionDetails;

@ControllerAdvice //Todos os controler tem que utilizar essa classe
public class RestExceptionHandler {

    //captura a excessão BadRequestException
    //se lançar excessão desse tipo, terá que retonar o que for definido
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(
        BadRequestException bre) {
            return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, check the documentation")
                .details(bre.getMessage())
                .developerMessage(bre.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST //precisa retornar o status
            );
        }
}

package com.champs.sprinboot2_essentials.handler;


import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.champs.sprinboot2_essentials.exception.BadRequestException;
import com.champs.sprinboot2_essentials.exception.BadRequestExceptionDetails;
import com.champs.sprinboot2_essentials.exception.ExceptionDetails;
import com.champs.sprinboot2_essentials.exception.ValidationExceptionDetails;

import io.micrometer.common.lang.Nullable;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice //Todos os controler tem que utilizar essa classe
@Log4j2 //Log4j2 é uma biblioteca de log
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //captura a excessão BadRequestException
    //se lançar excessão desse tipo, terá que retonar o que for definido
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(
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

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException manve, HttpHeaders headers,
        HttpStatus status, WebRequest request) { //

            //aqui irá criar uma lista de erros, irá separar por tipo, se tiver
            //mais de um erro, irá separar por vírgula
            List<FieldError> fieldErros = manve.getBindingResult().getFieldErrors();
            String fields = fieldErros.stream().map(FieldError::getField).collect(
                Collectors.joining(", "));//se tiver mais de um campo, separa por vírgula

            String fieldsMessage = fieldErros.stream().map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));
            
            return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, invalid fields")
                .details("Check the fields error")
                .developerMessage(manve.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(), HttpStatus.BAD_REQUEST //precisa retornar o status
            );
    }


    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, HttpStatus status, @Nullable Object body,
        HttpHeaders headers, WebRequest request) {
            
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
        .timestamp(LocalDateTime.now())
        .status(status.value())
        .title(ex.getCause().getMessage())
        .details(ex.getMessage())
        .developerMessage(ex.getClass().getName())
        .build();
        return ResponseEntity.status(status).headers(headers)
        .body(exceptionDetails);
    }
}

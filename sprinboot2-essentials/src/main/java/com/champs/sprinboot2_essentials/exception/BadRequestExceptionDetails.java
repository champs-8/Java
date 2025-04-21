package com.champs.sprinboot2_essentials.exception;

import lombok.Data;

import java.time.LocalDateTime;

import lombok.Builder;

@Data // Gera os métodos getters e setters
@Builder // Permite criar objetos usando o padrão Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}

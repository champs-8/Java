package com.champs.sprinboot2_essentials.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder // Permite criar objetos usando o padrão Builder
//padrão builder é um padrão de projeto que facilita a construção de objetos complexos
//com muitos parâmetros, tornando o código mais legível e fácil de manter
//@Data gera automaticamente os métodos getters, setters, equals, hashCode e toString
//para a classe, reduzindo a quantidade de código boilerplate que precisamos escrever
public class ExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}

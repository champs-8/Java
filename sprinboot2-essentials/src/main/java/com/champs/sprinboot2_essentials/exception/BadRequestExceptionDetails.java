package com.champs.sprinboot2_essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter // Gera métodos getters para os atributos da classe
@SuperBuilder // Permite herdar o padrão Builder da classe pai
public class BadRequestExceptionDetails extends ExceptionDetails {

}

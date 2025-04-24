package com.champs.sprinboot2_essentials.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TeamPostRequestBody {
    @NotEmpty(message = "The team name not cannot be empty") //não pode ser vazio
    //not empty ja verifica o nulo
    // @NotNull(message = "The team name not cannot be null") //não pode ser nulo
    private String name;
    private Long id;
    @URL(message = "The url is not valid") //verifica se é uma url válida
    private String url;
}


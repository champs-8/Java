package com.champs.UserApi.controller;

import com.champs.UserApi.model.User;
import com.champs.UserApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 

import java.util.List;
import java.util.Optional;

@RestController // informando que a classe é um controller
@RequestMapping("/users") // informando o path base para as requisições
public class Controller { 

    @Autowired // injetando a dependência
    private UserService userService; // instanciando a classe UserService

    @GetMapping // informando que o método é um GET
    public List<User> getAllUser() {
        return userService.getAllUser(); // retornando a lista de usuários
    }


}

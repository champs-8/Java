package com.champs.UserApi.service;

import com.champs.UserApi.model.User;
import com.champs.UserApi.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // informando que a classe é um serviço
public class UserService {

    @Autowired // injetando a dependência
    private UserRepository userRepository; // instanciando a classe UserRepository

    public List<User> getAllUser() {
        return userRepository.findAll(); // retornando a lista de usuários
    }
    public String hello(){
        return "Hello World"; // retornando hello world
    }
    public User createUser(User user){ // criando um usuário
        return userRepository.save(user); // retornando o usuário criado
        
    }
}

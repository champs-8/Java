package com.champs.UserApi.service;

import com.champs.UserApi.model.User;
import com.champs.UserApi.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired // injetando a dependência
    private UserRepository userRepository; // instanciando a classe UserRepository
 
    public List<User> getAllUser() {
        return userRepository.findAll(); // retornando a lista de usuários
    }
}

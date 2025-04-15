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
    public User getUserById(Long id){ // buscando um usuário pelo id
        return userRepository.findById(id).orElse(null); // retornando o usuário encontrado
    }
    public User updateUser(Long id, User user){ // atualizando um usuário
        User existingUser = userRepository.findById(id).orElse(null); // buscando o usuário pelo id
        if(existingUser != null) { // verificando se o usuário existe
            existingUser.setName(user.getName()); // atualizando o nome do usuário
            existingUser.setEmail(user.getEmail()); // atualizando o email do usuário
            existingUser.setPhone(user.getPhone()); // atualizando o telefone do usuário
            return userRepository.save(existingUser); // retornando o usuário atualizado
        } else {
            return null; // retornando null se o usuário não for encontrado
        }
    }
    public User deleteUser(Long id){ // deletando um usuário
        User existingUser = userRepository.findById(id).orElse(null); // buscando o usuário pelo id
        if(existingUser != null) { // verificando se o usuário existe
            userRepository.delete(existingUser); // deletando o usuário
            return existingUser; // retornando o usuário deletado
        } else {
            return null; // retornando null se o usuário não for encontrado
        }
    }
}

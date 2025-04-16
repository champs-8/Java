package com.champs.UserApi.controller;

import com.champs.UserApi.model.User;
import com.champs.UserApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // informando que a classe é um controller
@RequestMapping("/users") // informando o path base para as requisições
public class UserController {

    @Autowired // injetando a dependência
    private UserService userService; // instanciando a classe UserService

    @GetMapping // informando que o método é um GET
    public List<User> getAllUser() {
        return userService.getAllUser(); // retornando a lista de usuários
    }

    @PostMapping("/create") // informando que o método é um POST
    public User createUser(@RequestBody User user) { //recebendo usuario no corpo da requisição
        return userService.createUser(user); // retornando o usuário criado
    }
    
    @GetMapping("/hello")
    public String hello(){
        return userService.hello(); // retornando hello world
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) { //recebendo o id do usuario na url
        User user = userService.getUserById(id); // buscando o usuário pelo id
        if(user != null) { // verificando se o usuário existe
            return ResponseEntity.ok(user); // retornando o usuário encontrado
        } else {
            return ResponseEntity.notFound().build(); // retornando 404 se o usuário não for encontrado
        }
    }

    @PutMapping("/update/{id}") // informando que o método é um PUT
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) { //recebendo o id do usuario na url e o usuario no corpo da requisição
        User existingUser = userService.updateUser(id, user); // atualizando o usuário
        // System.out.println(existingUser); // retorn a posição na memoria
        if(existingUser != null) { // verificando se o usuário existe
            return ResponseEntity.ok(existingUser); // retornando o usuário atualizado
        } else {
            return ResponseEntity.notFound().build(); // retornando 404 se o usuário não for encontrado
        }
    }
    @DeleteMapping("/delete/{id}") // informando que o método é um DELETE
    public ResponseEntity<User> deleteUser(@PathVariable Long id) { //recebendo o id do usuario na url
        User user = userService.deleteUser(id); // buscando o usuário pelo id
        if(user != null) { // verificando se o usuário existe
            return ResponseEntity.ok(user); // retornando 200 se o usuário for deletado
        } else {
            return ResponseEntity.notFound().build(); // retornando 404 se o usuário não for encontrado
        }
    }
}

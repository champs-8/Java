package com.champs.UserApi.model;

import jakarta.persistence.*; //importar o entity

@Entity //indica que a classe é uma entidade
@Table(name = "users") //indica a tabela que será criada no banco de dados

public class User {

    @Id //indica que o atributo é uma chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o atributo é auto increment
    private Long id; //atributo id

    @Column(name = "name") //indica o nome da coluna no banco de dados
    private String name; //atributo name

    @Column(name = "email") //indica o nome da coluna no banco de dados
    private String email; //atributo email

    @Column(name = "phone") //indica o nome da coluna no banco de dados
    private String phone; //atributo telephone

    //construtor
    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    //construtor vazio
    public User() {
        
    }

    //getters e setters

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}

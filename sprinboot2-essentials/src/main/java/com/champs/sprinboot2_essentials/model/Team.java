package com.champs.sprinboot2_essentials.model;

import jakarta.persistence.*;

@Entity

public class Team {

    @Id //chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incremental id

    private long id;
    private String name;

    //constructor
    public Team(String name) {
        this.name = name;
    }

    //constructor vazio
    public Team() {
    }

    //getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

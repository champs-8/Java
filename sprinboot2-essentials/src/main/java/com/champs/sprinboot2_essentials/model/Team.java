package com.champs.sprinboot2_essentials.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id //chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incremental id

    private long id;

    //@Column(nullable = false, unique = true) //não pode ser nulo e não pode ter o mesmo nome
    private String name;

    public Team getBody() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBody'");
    }
}
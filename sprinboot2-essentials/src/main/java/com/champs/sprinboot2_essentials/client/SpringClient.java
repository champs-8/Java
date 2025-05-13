package com.champs.sprinboot2_essentials.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.champs.sprinboot2_essentials.model.Team;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Team> entity = new RestTemplate().getForEntity("http://localhost:8080/teams/2",
        Team.class); //Team.class é o tipo de retorno esperado
        // RestTemplate é uma classe do Spring que facilita a comunicação com APIs REST
        // O método getForEntity faz uma requisição GET para a URL especificada

        log.info(entity); //mais informações

        Team object = new RestTemplate().getForObject("http://localhost:8080/teams/{id}",
        Team.class, 5);
        // O método getForObject faz uma requisição GET para a URL especificada e retorna o objeto do tipo esperado
        // O {id} é um placeholder que será substituído pelo valor passado como argumento

        log.info(object); //mais enxuto
    }
}

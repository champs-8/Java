package com.champs.sprinboot2_essentials.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.champs.sprinboot2_essentials.model.Team;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Team> entity = new RestTemplate().getForEntity("http://localhost:8080/teams/1",
        Team.class); //Team.class é o tipo de retorno esperado
        // RestTemplate é uma classe do Spring que facilita a comunicação com APIs REST
        // O método getForEntity faz uma requisição GET para a URL especificada

        log.info(entity); //mais informações

        Team object = new RestTemplate().getForObject("http://localhost:8080/teams/{id}",
        Team.class, 5);
        // O método getForObject faz uma requisição GET para a URL especificada e retorna o objeto do tipo esperado
        // O {id} é um placeholder que será substituído pelo valor passado como argumento
        log.info(object); //mais enxuto


        Team[] times = new RestTemplate().getForObject("http://localhost:8080/teams/all",
        Team[].class, 5);
        log.info(Arrays.toString(times));
        
        
        ResponseEntity<List<Team>> exchange =  new RestTemplate().exchange("http://localhost:8080/teams/all",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>(){});
        log.info(exchange.getBody());
        
        // post
        Team barcelona = Team.builder().name("Barcelona").build();
        Team barcelonaSaved = new RestTemplate().postForObject("http://localhost:8080/teams/", barcelona, Team.class);
        log.info("Saved team: {}", barcelonaSaved);
        
        // put
        Team teamSaved = barcelonaSaved.getBody();
        teamSaved.setName("Barcelona FC");

        ResponseEntity<Void> barcelonaUpdate = new RestTemplate().exchange("http://localhost:8080/teams/",HttpMethod.PUT, new HttpEntity<>(teamSaved, createJsonHeader()), Void.class);
        log.info("Updated team: {}", barcelonaUpdate);

        //POST
        Team milan = Team.builder().name("milan").build();
        ResponseEntity<Team> milanSaved = new RestTemplate().exchange("http://localhost:8080/teams/", HttpMethod.POST, new HttpEntity<>(milan, createJsonHeader()), Team.class);
        log.info("Saved team: {}", milanSaved);


        //DELETE
        // Team milan = Team.builder().name("milan").build();
        // ResponseEntity<Team> milanSaved = new RestTemplate().exchange("http://localhost:8080/teams/", HttpMethod.POST, new HttpEntity<>(milan, createJsonHeader()), Team.class);
        // log.info("Saved team: {}", milanSaved);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}

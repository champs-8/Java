package com.champs.sprinboot2_essentials.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.request.TeamPostRequestBody;
import com.champs.sprinboot2_essentials.request.TeamPutRequestBody;
import com.champs.sprinboot2_essentials.services.TeamService;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@RestController
@Log4j2
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}") //endpoint quando entrar na rota teams/{id}
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findById(id));
    }

    @GetMapping("/find/{name}") //endpoint quando entrar na rota teams/{name}
    //Buscar por nome
    public ResponseEntity<List<Team>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(teamService.findByName(name));
    }

    @GetMapping //endpoint quando entrar na rota teams para buscar todos os objetos
    public ResponseEntity<Page<Team>> lista(Pageable pageable) {
        return ResponseEntity.ok(teamService.listaAll(pageable));
        //pageble é um objeto que contém informações de paginação
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Team>> listAll() {
        return ResponseEntity.ok(teamService.listaAllNonPageable());
        //Não recebe parâmetros de paginação
    }

    @PostMapping //endpoint para criar um novo objeto
    public ResponseEntity<Team> save(@RequestBody @Valid TeamPostRequestBody teamPostRequestBody) {
        //@valid faz a validação dos atributos do objeto
        return new ResponseEntity<>(
            teamService.save(teamPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") //endpoint quando entrar na rota teams/{id} para deletar o objeto
    //o id é passado como parametro
    //o @PathVariable indica que o id é um parametro da url
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // Endpoint para atualizar o objeto
    public ResponseEntity<Void> replace(@PathVariable Long id,
        @RequestBody TeamPutRequestBody teamPutRequestBody) {
        teamPutRequestBody.setId(id); // Força o uso do id da URL
        teamService.replace(teamPutRequestBody);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}

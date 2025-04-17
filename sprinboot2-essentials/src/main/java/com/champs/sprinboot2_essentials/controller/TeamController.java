package com.champs.sprinboot2_essentials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.services.TeamServices;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamServices teamServices;

    @GetMapping //endpoint quando entrar na rota teams
    public List<Team> lista() {
        return teamServices.lista();
    }
    @PostMapping("/create") //endpoint criar time
    public Team createTeam(@RequestBody Team team){ //essa requisição será coletada no corpo
        return teamServices.createTeam(team);
    }
}

package com.champs.sprinboot2_essentials.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.repository.TeamRepository;

@Service
public class TeamServices {

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team){
        return teamRepository.save(team); //servico para criar o objeto
    }

    public List<Team> lista() {
        return teamRepository.findAll(); //buscar por todos os objetos
    }
}

package com.champs.sprinboot2_essentials.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.champs.sprinboot2_essentials.mapper.TeamMapper;
import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.repository.TeamRepository;
import com.champs.sprinboot2_essentials.request.TeamPostRequestBody;
import com.champs.sprinboot2_essentials.request.TeamPutRequestBody;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    //servico para criar o objeto
    public Team save(TeamPostRequestBody teamPostRequestBory) {
        return teamRepository.save(TeamMapper.INSTANCE.toTeam(teamPostRequestBory));
    }

    public List<Team> lista() {
        return teamRepository.findAll(); //buscar por todos os objetos
    }

    public Team findById(Long id) {
        return findByIdOrThrowBadRequestException(id);
    }//busca por id
    

    public void replace(TeamPutRequestBody teamPutRequestBody) {
        Team savedTeam = findByIdOrThrowBadRequestException((Long) teamPutRequestBody.getId());
        Team team = TeamMapper.INSTANCE.toTeam(teamPutRequestBody);
        team.setId(savedTeam.getId());
        teamRepository.save(team);
    }

    public Team findByIdOrThrowBadRequestException(Long id) {
        return teamRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Team not found"
            ));
    }

    public void delete(Long id) {
        teamRepository.delete(findById(id));
    }
}

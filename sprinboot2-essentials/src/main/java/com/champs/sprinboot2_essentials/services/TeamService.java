package com.champs.sprinboot2_essentials.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.champs.sprinboot2_essentials.exception.BadRequestException;
import com.champs.sprinboot2_essentials.mapper.TeamMapper;
import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.repository.TeamRepository;
import com.champs.sprinboot2_essentials.request.TeamPostRequestBody;
import com.champs.sprinboot2_essentials.request.TeamPutRequestBody;

import jakarta.transaction.Transactional;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional(rollbackOn = Exception.class ) //transação, se der erro, não salva no banco
    //(rollbackOn = Exception.class ) //faz com que qualquer excessão gere rollbacks
    //servico para criar o objeto
    public Team save(TeamPostRequestBody teamPostRequestBory) {
        return teamRepository.save(TeamMapper.INSTANCE.toTeam(teamPostRequestBory));
    }

    public Page<Team> listaAll(Pageable pageable) {
        return teamRepository.findAll(pageable); //buscar por todos os objetos
    }
    public List<Team> findByName(String name) {
        return teamRepository.findByName(name); //buscar por nome
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
            .orElseThrow(() -> new BadRequestException("Team not found"));
            //excessão personalizada
    }

    public void delete(Long id) {
        teamRepository.delete(findById(id));
    }
}

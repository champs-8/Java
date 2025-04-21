package com.champs.sprinboot2_essentials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.champs.sprinboot2_essentials.model.Team;
import com.champs.sprinboot2_essentials.request.TeamPostRequestBody;
import com.champs.sprinboot2_essentials.request.TeamPutRequestBody;

@Mapper(componentModel = "spring") //permite a injeção de dependência
public abstract class TeamMapper {
    public static final TeamMapper INSTANCE = Mappers.getMapper(
        TeamMapper.class); //instancia o mapper

    //mapeia o objeto de entrada para o objeto de saída
    public abstract Team toTeam(TeamPostRequestBody teamPostRequestBody);
    public abstract Team toTeam(TeamPutRequestBody teamPutRequestBody);
}

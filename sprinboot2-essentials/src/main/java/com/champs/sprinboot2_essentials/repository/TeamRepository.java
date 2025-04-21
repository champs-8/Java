package com.champs.sprinboot2_essentials.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.champs.sprinboot2_essentials.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByName(String name); //método para buscar por nome
}

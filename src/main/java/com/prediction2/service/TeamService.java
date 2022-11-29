package com.prediction2.service;

import com.prediction2.entity.Team;
import com.prediction2.excpetion.EntityNotFoundException;
import com.prediction2.repository.TeamRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll(Sort.by("team"));
    }

    public Team findById(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return optionalTeam.get();
    }
}

package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.repository.TeamRepository;

@Service
public final class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team){
        return teamRepository.save(team);
    }

    public Team updateTeam(Team team){
        return teamRepository.save(team);
    }

    private void validate(Team team){

    }
}

package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamFacadeImpl implements TeamFacade {
    @Autowired
    private BeanMappingService beanMappingService;
    private TeamService teamService;

    @Override
    public Long create(TeamDto team) {
        Team mappedTeam = beanMappingService.mapTo(team, Team.class);
        teamService.create(mappedTeam);
        return mappedTeam.getId();
    }

    @Override
    public void remove(Long id) {
        teamService.remove(teamService.findById(id));
    }

    @Override
    public TeamDto findTeamById(Long id) {
        Team team = teamService.findById(id);
        return (team == null) ? null : beanMappingService.mapTo(team, TeamDto.class);
    }

    @Override
    public TeamDto findTeamByName(String name) {
        Team team = teamService.findByName(name);
        return (team == null) ? null : beanMappingService.mapTo(team, TeamDto.class);
    }

    @Override
    public List<TeamDto> findAllTeams() {
        return beanMappingService.mapTo(teamService.findAll(), TeamDto.class);
    }
}

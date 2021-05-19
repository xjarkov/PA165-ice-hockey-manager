package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kristian Kosorin (456620)
 */

@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {
    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private TeamService teamService;

    @Override
    public Long create(TeamDto team) {
        Team mappedTeam = beanMappingService.mapTo(team, Team.class);
        Team createdTeam = teamService.create(mappedTeam);
        return createdTeam.getId();
    }

    @Override
    public void remove(TeamDto team) {
        teamService.remove(beanMappingService.mapTo(team, Team.class));
    }

    @Override
    public void update(TeamDto team) {
        teamService.update(beanMappingService.mapTo(team, Team.class));
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

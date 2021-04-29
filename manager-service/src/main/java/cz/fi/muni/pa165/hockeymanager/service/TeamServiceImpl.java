package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public void create(Team team) {
        teamDao.create(team);
    }

    @Override
    public void remove(Team team) {
        teamDao.remove(team);
    }

    @Override
    public Team findByName(String name) {
        return teamDao.findByName(name);
    }

    @Override
    public Team findById(Long id) {
        return teamDao.findById(id);
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public void addPlayer(Team team, HockeyPlayer hockeyPlayer) {
        if (team.getHockeyPlayers().contains(hockeyPlayer)) {
            throw new ManagerServiceException(String.format(
                    "The player %s is already in the %s team",
                    hockeyPlayer.getFullName(),
                    team.getName()
            ));
        }

        if (hockeyPlayer.getTeam() != null) {
            throw new ManagerServiceException(String.format(
                    "The player %s is already in the %s team",
                    hockeyPlayer.getFullName(),
                    hockeyPlayer.getTeam().getName()
            ));
        }

        team.addPlayer(hockeyPlayer);
        hockeyPlayer.setTeam(team); // do i do this here??? do i do it liek this?
    }

    @Override
    public void removePlayer(Team team, HockeyPlayer hockeyPlayer) {
        if (!team.getHockeyPlayers().contains(hockeyPlayer)) {
            throw new ManagerServiceException(String.format(
                    "The player %s is not in the %s team",
                    hockeyPlayer.getFullName(),
                    team.getName()
            ));
        }

        team.removePlayer(hockeyPlayer);
        hockeyPlayer.setTeam(null); // do i do this here???
    }

    @Override
    public void addMatch(Team team, Match match) {
        if (!(match.getHomeTeam().equals(team) && match.getVisitingTeam().equals(team))) {
            throw new ManagerServiceException(String.format(
                    "The match with ID %d does not contain %s team",
                    match.getId(),
                    team.getName()
            ));
        }

        team.addMatch(match);
    }

    @Override
    public void removeTheNearestMatch(Team team) {
        if (team.getMatches().isEmpty()) {
            throw new ManagerServiceException(String.format(
                    "The %s team does not have any incoming matches",
                    team.getName()
            ));
        }

        team.removeMatch(Collections.min(team.getMatches(), Comparator.comparing(Match::getDateTime)));
    }
}

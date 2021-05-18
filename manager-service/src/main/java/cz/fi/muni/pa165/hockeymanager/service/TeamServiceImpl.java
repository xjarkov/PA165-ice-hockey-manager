package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Matus Jarkovic 456441
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(Team team) {
        try {
            teamDao.create(team);
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while creating team with message: %s",
                    e.getMessage()
            ));
        }
        return team;
    }

    @Override
    public void remove(Team team) {
        try {
            teamDao.remove(team);
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while removing team with name: %s, with message: %s",
                    team.getName(),
                    e.getMessage()
            ));
        }
    }

    @Override
    public void update(Team team) {
        try {
            teamDao.update(team);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not update match " + team + " exception: " + e);
        }
    }

    @Override
    public Team findByName(String name) {
        try{
            return teamDao.findByName(name);
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while finding team by name: %s, with message: %s",
                    name,
                    e.getMessage()
            ));
        }
    }

    @Override
    public Team findById(Long id) {
        try{
            return teamDao.findById(id);
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while finding team by id: %d, with message: %s",
                    id,
                    e.getMessage()
            ));
        }
    }

    @Override
    public List<Team> findAll() {
        try{
            return teamDao.findAll();
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while finding all teams with message: %s",
                    e.getMessage()
            ));
        }
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
        if (!(match.getHomeTeam().equals(team) || match.getVisitingTeam().equals(team))) {
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

    @Override
    public HockeyPlayer findBestPlayer(Team team){
        if(team.getHockeyPlayers().isEmpty()){
            throw new ManagerServiceException(String.format(
                    "The %s team does not have any players",
                    team.getName()
            ));
        }
        Set<HockeyPlayer> players = team.getHockeyPlayers();
        HockeyPlayer bestPlayer = new HockeyPlayer("","",0,0);
        for(HockeyPlayer p : players){
            if((p.getDefensiveStrength() + p.getOffensiveStrength()) >
                    (bestPlayer.getDefensiveStrength() + bestPlayer.getOffensiveStrength())){
                bestPlayer = p;
            }
        }
        return bestPlayer;
    }
}

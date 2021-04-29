package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public void addPlayer(Team team, HockeyPlayer player) {

    }

    @Override
    public void removePlayer(Team team, HockeyPlayer player) {

    }

    @Override
    public void addMatch(Team team, Match match) {

    }

    @Override
    public void removeTheNearestMatch(Team team) {

    }
}

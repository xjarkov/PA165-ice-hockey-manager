package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
@Service
public class HockeyPlayerServiceImpl implements HockeyPlayerService {
    @Autowired
    private HockeyPlayerDao hockeyPlayerDao;

    @Autowired
    private MatchDao matchDao;

    @Override
    public HockeyPlayer create(HockeyPlayer player) {
        try {
            hockeyPlayerDao.create(player);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not create player " + player + " exc: " + e);
        }
        return player;
    }

    @Override
    public void remove(HockeyPlayer player) {
        try {
            hockeyPlayerDao.remove(player);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not remove player " + player + " exc: " + e);
        }
    }

    @Override
    public void update(HockeyPlayer player) {
        try {
            hockeyPlayerDao.update(player);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not update player " + player + " exc: " + e);
        }
    }

    @Override
    public HockeyPlayer findById(Long id) {
        HockeyPlayer player;
        try {
            player = hockeyPlayerDao.findById(id);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not find player with id: " + id + "exc: " + e);
        }
        return player;
    }

    @Override
    public List<HockeyPlayer> findAll() {
        List<HockeyPlayer> playerList;
        try {
            playerList = hockeyPlayerDao.findAll();
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not find all players exc: " + e);
        }
        return playerList;
    }

    @Override
    public List<Match> findPlayerTeamMatches(HockeyPlayer player) {
        List<Match> matches;
        try {
            matches = matchDao.findByTeam(player.getTeam());
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not find all matches of team exc: " + e);
        }
        return matches;
    }
}
